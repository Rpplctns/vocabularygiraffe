package radoslawmajer.vocabularygiraffe.server.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import radoslawmajer.vocabularygiraffe.server.KNOWN_ERRORS
import radoslawmajer.vocabularygiraffe.server.data.*
import radoslawmajer.vocabularygiraffe.server.services.*
import radoslawmajer.vocabularygiraffe.server.utils.*

@RestController
@RequestMapping("/api/quiz")
class QuizController (val service: QuizService) {
    @GetMapping("/")
    fun getQuiz(@RequestParam("token") token: String): ResponseEntity<Any> =
        try {
            ResponseEntity.ok(service.getQuiz(validateAndGetId(token)))
        } catch (e: Exception) {
            if (e.message in KNOWN_ERRORS) ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.message)
            else ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown error")
        }
}