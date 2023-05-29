package radoslawmajer.vocabularygiraffe.server.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import radoslawmajer.vocabularygiraffe.server.services.UserService
import java.util.*

@RestController
@RequestMapping("/api/users")
class UserController (val service: UserService) {
    @GetMapping("/login/")
    fun login(@RequestParam("login") login: String, @RequestParam("password") password: String): ResponseEntity<String> =
        ResponseEntity.ok(service.login(login, password))

    @GetMapping("/register/")
    fun register(@RequestParam("login") login: String, @RequestParam("password") password: String): ResponseEntity<String> =
        ResponseEntity.ok(service.register(login, password))

    @DeleteMapping("/")
    fun deleteUser(@RequestParam("login") login: String) =
        service.deleteUser(login)
}