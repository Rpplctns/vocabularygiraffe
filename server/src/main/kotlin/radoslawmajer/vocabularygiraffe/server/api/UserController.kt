package radoslawmajer.vocabularygiraffe.server.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import radoslawmajer.vocabularygiraffe.server.data.validateAndGetId
import radoslawmajer.vocabularygiraffe.server.services.UserService
import java.util.*

@RestController
@RequestMapping("/api/users")
class UserController (val service: UserService) {
    @GetMapping("/login/")
    fun login(@RequestParam("login") login: String, @RequestParam("password") password: String): ResponseEntity<String> =
        ResponseEntity.ok(service.login(login, password))

    @GetMapping("/refresh/")
    fun refresh(@RequestParam("login") login: String, @RequestParam("token") token: String): ResponseEntity<String> =
        ResponseEntity.ok(service.refresh(login, validateAndGetId(token)))

    @GetMapping("/register/")
    fun register(@RequestParam("login") login: String, @RequestParam("password") password: String): ResponseEntity<String> =
        ResponseEntity.ok(service.register(login, password))

    @DeleteMapping("/")
    fun deleteUser(@RequestParam("login") login: String, @RequestParam("token") token: String) =
        service.deleteUser(login, validateAndGetId(token))
}