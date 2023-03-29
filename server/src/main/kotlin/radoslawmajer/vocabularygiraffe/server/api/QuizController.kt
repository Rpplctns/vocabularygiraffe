package radoslawmajer.vocabularygiraffe.server.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import radoslawmajer.vocabularygiraffe.server.database.Quiz
import radoslawmajer.vocabularygiraffe.server.database.Results
import radoslawmajer.vocabularygiraffe.server.services.QuizService


@RestController
@RequestMapping("/api/quiz")
class QuizController (val service: QuizService) {
    @GetMapping("/")
    fun getQuiz(): Quiz = service.getQuiz()

    @PostMapping("/")
    fun postResults(@RequestBody results: Results) {
        service.acceptResults(results)
    }
}