package radoslawmajer.vocabularygiraffe.server.data

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "WORDS", indexes = [ Index(name = "index_used", columnList = "used") ])
data class Word (
    @Id
    @GeneratedValue(generator = "uuid2")
    var id: UUID = UUID.randomUUID(),

    @Column(name = "content")
    val content: String?,

    @Column(name = "type")
    val type: String?,

    @Column(name = "status")
    var status: Int?,

    @Column(name = "category")
    var category: Int?,

    @Column(name = "used")
    var used: LocalDateTime? = null
) {
    constructor(): this(UUID.randomUUID(), null, null, null, null, null)
}