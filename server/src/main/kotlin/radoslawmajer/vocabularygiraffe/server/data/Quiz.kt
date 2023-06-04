package radoslawmajer.vocabularygiraffe.server.data

import java.util.*

data class Exercise (
    val id: UUID?,
    val sentence: String?,
    val word: String?,
    val type: String?
)

data class Quiz (
    val exercises: List<Exercise>?
)