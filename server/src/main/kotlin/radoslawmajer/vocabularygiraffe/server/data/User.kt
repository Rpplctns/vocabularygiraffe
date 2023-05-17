package radoslawmajer.vocabularygiraffe.server.data

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

@Entity
@Table(name = "user")
class User(
    @Id
    @GeneratedValue(generator = "uuid2")
    var id: UUID = UUID.randomUUID(),

    @Column(name = "email")
    val email: String,

    @Column(name = "username")
    val username: String,

    @Column(name = "passwordHash")
    var password: String

) : UserDetails {
    constructor() : this(UUID.randomUUID(), "", "", "")

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        TODO("Not yet implemented")
    }

    override fun getPassword(): String = password
    override fun getUsername(): String = username

    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}