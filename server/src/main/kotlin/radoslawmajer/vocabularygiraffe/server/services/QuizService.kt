package radoslawmajer.vocabularygiraffe.server.services

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import radoslawmajer.vocabularygiraffe.server.database.Exercise
import radoslawmajer.vocabularygiraffe.server.database.Quiz
import radoslawmajer.vocabularygiraffe.server.database.Results
import java.time.LocalDateTime

@Service
class QuizService(val db: JdbcTemplate) {
    fun getQuiz(): Quiz = Quiz(
        db.query("select * from words order by last_time_used limit 10") { response, _ ->
            Exercise(
                response.getString("id"),
                "",
                response.getString("content"),
                response.getString("word_type")
            )
        }
    )

    fun acceptResults(results: Results) {
        val time = LocalDateTime.now()
        for(r in results.exercises) {
            db.update("update words set last_time_used = (?) where id = (?)", time, r.first)
        }
    }
}