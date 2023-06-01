package radoslawmajer.vocabularygiraffe.server.data

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "T_WORD", indexes = [ Index(name = "index_used", columnList = "used"), Index(name = "index_owner", columnList = "owner") ])
data class Word (
    @Id
    @GeneratedValue(generator = "uuid2")
    var id: UUID? = UUID.randomUUID(),

    @Column(name = "content")
    val content: String?,

    @Column(name = "type")
    val type: String?,

    @Column(name = "status")
    var status: Int?,

    @Column(name = "category")
    var category: Int?,

    @Column(name = "used")
    var used: LocalDateTime?,

    @Column(name = "owner")
    var user: UUID?
) {
    constructor(): this(null, null, null, null, null, null, null)
}