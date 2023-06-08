package radoslawmajer.vocabularygiraffe.server.services

import io.ktor.utils.io.core.*
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service
import radoslawmajer.vocabularygiraffe.server.data.User
import radoslawmajer.vocabularygiraffe.server.data.areMatching
import radoslawmajer.vocabularygiraffe.server.data.generate
import radoslawmajer.vocabularygiraffe.server.data.hash
import radoslawmajer.vocabularygiraffe.server.repository.UserRepository
import java.util.*

@Service
class UserService (var repository: UserRepository) {
    @Throws(Exception::class)
    fun login(login: String, password: String): String {
        val userRecord = repository.findOne(Example.of(User(null, login, null)))
        if(userRecord.isEmpty) throw Exception("User does not exist")
        if(!areMatching(password, userRecord.get().passwordHash!!)) throw Exception("Incorrect password")
        return generate(userRecord.get().id!!).encrypt()
    }

    @Throws(Exception::class)
    fun register(login: String, password: String): String {
        if(login.length > 20) throw Exception("Username is too long")
        if(login.isEmpty()) throw Exception("Username must not be empty")
        if(!login.matches(Regex("^[a-zA-Z0-9]+$"))) throw Exception("Username must contain only alphabet letters and numbers")

        if(password.length > 30) throw Exception("Password is too long")
        if(password.length < 5) throw Exception("Password is too short")
        if(password.isEmpty()) throw Exception("Password must not be empty")
        if(!(
                    password.contains(Regex("[A-Z]")) &&
                            password.contains(Regex("[a-z]")) &&
                            password.contains(Regex("\\d"))
                )
            )
            throw Exception("Password must contain at least one small letter, one capital letter and one number")

        if(repository.exists(Example.of(User(null, login, null))))
            throw Exception("User already exists")
        val userRecord = repository.save(User(name = login, passwordHash = hash(password)))
        return generate(userRecord.id!!).encrypt()
    }

    @Throws(Exception::class)
    fun deleteUser(login: String, user: UUID) {
        val userRecord = repository.findOne(Example.of(User(null, login, null)))
        if(userRecord.get().id != user) throw Exception("Invalid token")
        if(userRecord.isEmpty) throw Exception("User does not exist")
        repository.deleteById(userRecord.get().id!!)
    }

    @Throws(Exception::class)
    fun refresh(login: String, user: UUID): String? {
        val userRecord = repository.findOne(Example.of(User(null, login, null)))
        if(userRecord.get().id != user) throw Exception("Invalid token")
        return generate(userRecord.get().id!!).encrypt()
    }
}