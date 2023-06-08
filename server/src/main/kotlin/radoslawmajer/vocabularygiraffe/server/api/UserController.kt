package radoslawmajer.vocabularygiraffe.server.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import radoslawmajer.vocabularygiraffe.server.KNOWN_ERRORS
import radoslawmajer.vocabularygiraffe.server.data.validateAndGetId
import radoslawmajer.vocabularygiraffe.server.services.UserService
import java.util.*

@RestController
@RequestMapping("/api/users")
class UserController (val service: UserService) {
    @GetMapping("/login/")
    fun login(@RequestParam("login") login: String, @RequestParam("password") password: String): ResponseEntity<String> =
        try {
            ResponseEntity.ok(service.login(login, password))
        } catch (e: Exception) {
            if (e.message in KNOWN_ERRORS) ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.message)
            else ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown error")
        }

    @GetMapping("/refresh/")
    fun refresh(@RequestParam("login") login: String, @RequestParam("token") token: String): ResponseEntity<String> =
        try {
            ResponseEntity.ok(service.refresh(login, validateAndGetId(token)))
        } catch (e: Exception) {
            if(e.message in KNOWN_ERRORS) ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
            else ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown error")
        }

    @GetMapping("/register/")
    fun register(@RequestParam("login") login: String, @RequestParam("password") password: String): ResponseEntity<String> =
        try {
            ResponseEntity.ok(service.register(login, password))
        } catch (e: Exception) {
            if(e.message in KNOWN_ERRORS) ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
            else ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown error")
        }

    @DeleteMapping("/")
    fun deleteUser(@RequestParam("login") login: String, @RequestParam("token") token: String) =
        try {
            service.deleteUser(login, validateAndGetId(token))
        } catch (e: Exception) {
            if(e.message in KNOWN_ERRORS) ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
            else ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown error")
        }
}