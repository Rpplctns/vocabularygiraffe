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
        val userRecord = repository.findOne(Example.of(User(null, login, null)))
        if(userRecord.isEmpty) throw Exception("user does not exist")
        if(!areMatching(password, userRecord.get().passwordHash!!)) throw Exception("wrong password")
        return generate(userRecord.get().id!!).encrypt()
    }

    @Throws(Exception::class)
    fun register(login: String, password: String): String {
        if(repository.exists(Example.of(User(null, login, null))))
            throw Exception("user already exists")
        val userRecord = repository.save(User(name = login, passwordHash = hash(password)))
        return generate(userRecord.id!!).encrypt()
    }

    @Throws(Exception::class)
    fun deleteUser(login: String, user: UUID) {
        val userRecord = repository.findOne(Example.of(User(null, login, null)))
        if(userRecord.get().id != user) throw Exception("wrong token")
        if(userRecord.isEmpty) throw Exception("user does not exist")
        repository.deleteById(userRecord.get().id!!)
    }

    @Throws(Exception::class)
    fun refresh(login: String, user: UUID): String? {
        val userRecord = repository.findOne(Example.of(User(null, login, null)))
        if(userRecord.get().id != user) throw Exception("wrong token")
        return generate(userRecord.get().id!!).encrypt()
    }
}