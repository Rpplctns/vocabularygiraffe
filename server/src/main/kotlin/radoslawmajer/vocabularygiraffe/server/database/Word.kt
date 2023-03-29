package radoslawmajer.vocabularygiraffe.server.database

import java.sql.Timestamp

class Word (
    val id: String?,
    val content: String?,
    val lastTimeUsed: Timestamp?,
    val type: String?
)