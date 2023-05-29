package radoslawmajer.vocabularygiraffe.server.data

import jakarta.persistence.*
import org.mindrot.jbcrypt.BCrypt
import java.util.*

@Entity
@Table(name = "T_USER")
class User (
    @Id
    @GeneratedValue(generator = "uuid2")
    var id: UUID? = UUID.randomUUID(),

    @Column(name = "name")
    var name: String?,

    @Column(name = "password_hash")
    var passwordHash: String?
) {
    constructor(): this(null, null, null)
}

fun hash(password: String): String = BCrypt.hashpw(password, BCrypt.gensalt())
fun areMatching(password: String, passwordHash: String): Boolean = BCrypt.checkpw(password, passwordHash)