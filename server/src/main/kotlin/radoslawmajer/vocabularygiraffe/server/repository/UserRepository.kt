package radoslawmajer.vocabularygiraffe.server.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import radoslawmajer.vocabularygiraffe.server.data.User
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, UUID>