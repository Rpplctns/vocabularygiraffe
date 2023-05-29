package radoslawmajer.vocabularygiraffe.server.api

import org.springframework.web.bind.annotation.*
import radoslawmajer.vocabularygiraffe.server.data.*
import radoslawmajer.vocabularygiraffe.server.services.*
import radoslawmajer.vocabularygiraffe.server.utils.*

@RestController
@RequestMapping("/api/quiz")
class QuizController (val service: QuizService) {
    @GetMapping("/")
    fun getQuiz(@RequestParam("token") token: String): Quiz =
        service.getQuiz(validateAndGetId(token))

    @PostMapping("/")
    fun postResults(@RequestBody results: Results, @RequestParam("token") token: String) =
        service.acceptResults(results, validateAndGetId(token))
}