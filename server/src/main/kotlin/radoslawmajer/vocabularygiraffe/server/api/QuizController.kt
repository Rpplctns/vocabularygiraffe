package radoslawmajer.vocabularygiraffe.server.api

import org.springframework.web.bind.annotation.*
import radoslawmajer.vocabularygiraffe.server.database.*
import radoslawmajer.vocabularygiraffe.server.services.*
import radoslawmajer.vocabularygiraffe.server.utils.*

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