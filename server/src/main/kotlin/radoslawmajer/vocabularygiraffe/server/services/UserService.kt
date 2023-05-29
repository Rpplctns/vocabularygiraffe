package radoslawmajer.vocabularygiraffe.server.services

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
        val user = repository.findOne(Example.of(User(null, login, null)))
        if(user.isEmpty) throw Exception("user does not exist")
        if(areMatching(password, user.get().passwordHash!!)) throw Exception("wrong password")
        return generate(user.get().id!!).encrypt()
    }

    @Throws(Exception::class)
    fun register(login: String, password: String): String {
        if(repository.exists(Example.of(User(null, login, null))))
            throw Exception("user already exists")
        val user = repository.save(User(name = login, passwordHash = hash(password)))
        return generate(user.id!!).encrypt()
    }

    fun deleteUser(login: String) {
        val user = repository.findOne(Example.of(User(null, login, null)))
        if(user.isEmpty) throw Exception("user does not exist")
        repository.deleteById(user.get().id!!)
    }
}