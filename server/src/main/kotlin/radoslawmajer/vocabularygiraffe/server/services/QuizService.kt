package radoslawmajer.vocabularygiraffe.server.services

import com.aallam.openai.client.OpenAI
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import radoslawmajer.vocabularygiraffe.server.database.Exercise
import radoslawmajer.vocabularygiraffe.server.database.Quiz
import radoslawmajer.vocabularygiraffe.server.database.Results
import radoslawmajer.vocabularygiraffe.server.database.Word
import radoslawmajer.vocabularygiraffe.server.utils.getSentence
import java.time.LocalDateTime


@Service
class QuizService(val db: JdbcTemplate) {

    @Autowired
    private lateinit var openAI: OpenAI

    /**
     * Generates quiz.
     * @return quiz
     */
    fun getQuiz(): Quiz {
        val words = db.query("select * from words order by last_time_used limit 10") { response, _ ->
            Word(
                response.getString("id"),
                response.getString("content"),
                response.getTimestamp("last_time_used"),
                response.getString("word_type"),
                response.getInt("status")
            )
        }
        return Quiz(List(10) { q ->
            Exercise(
                words[q].id,
                runBlocking { getSentence(words[q].content, openAI) },
                words[q].content,
                words[q].type,
                words[q].status
            )
        })
    }

    /**
     * Process results of the quiz. Updates the date of giving the exercise last time
     * @param results results of the quiz
     */
    fun acceptResults(results: Results) {
        val time = LocalDateTime.now()
        for (r in results.exercises) {
            db.update("update words set last_time_used = (?) and status = (?) where id = (?)", time, r.second, r.first)
        }
    }
}