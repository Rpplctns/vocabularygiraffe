package radoslawmajer.vocabularygiraffe.server.repository

import org.springframework.data.jpa.repository.JpaRepository
import radoslawmajer.vocabularygiraffe.server.data.User
import java.util.*

interface UserRepository : JpaRepository<User, UUID> {
    fun findByEmail(email: String): Optional<User>
}