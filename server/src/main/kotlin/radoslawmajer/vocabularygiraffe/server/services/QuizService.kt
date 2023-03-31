package radoslawmajer.vocabularygiraffe.server.services

import radoslawmajer.vocabularygiraffe.server.database.*
import radoslawmajer.vocabularygiraffe.server.utils.*

import com.aallam.openai.client.OpenAI
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
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
        val result = db.query("select * from words order by last_time_used limit 10") { response, _ ->
            Exercise(
                response.getString("id"),
                "",
                response.getString("content"),
                response.getString("word_type")
            )
        }
        for(exercise in result) {
            val sentence = exercise.word?.let { runBlocking { getSentence(it, openAI) } }
            if (sentence != null) {
                exercise.sentence = sentence.substring(2)
            }
        }
        return Quiz(result)
    }

    /**
     * Process results of the quiz. Updates the date of giving the exercise last time
     * @param results results of the quiz
     */
    fun acceptResults(results: Results) {
        val time = LocalDateTime.now()
        for (r in results.exercises) {
            db.update("update words set last_time_used = (?) where id = (?)", time, r.first)
        }
    }
}