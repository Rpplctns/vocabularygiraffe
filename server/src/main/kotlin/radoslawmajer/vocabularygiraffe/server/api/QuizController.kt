package radoslawmajer.vocabularygiraffe.server.api

import org.springframework.web.bind.annotation.*
import radoslawmajer.vocabularygiraffe.server.data.*
import radoslawmajer.vocabularygiraffe.server.services.*
import radoslawmajer.vocabularygiraffe.server.utils.*

@RestController
@RequestMapping("/api/quiz")
class QuizController (val service: QuizService) {
    @GetMapping("/")
    @ResponseBody
    fun getQuiz(): Quiz = service.getQuiz()

    @PostMapping("/")
    fun postResults(@RequestBody results: Results) {
        service.acceptResults(results)
    }
}