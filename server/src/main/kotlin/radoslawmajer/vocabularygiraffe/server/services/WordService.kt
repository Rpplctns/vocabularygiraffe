package radoslawmajer.vocabularygiraffe.server.services

import org.springframework.stereotype.Service
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import radoslawmajer.vocabularygiraffe.server.database.Word
import java.time.LocalDateTime
import java.util.*

@Service
class WordService(val db: JdbcTemplate) {
    fun getWords(): List<Word> = db.query("select * from words") { response, _ ->
        Word(
            response.getString("id"),
            response.getString("content"),
            response.getTimestamp("last_time_used"),
            response.getString("word_type")
        )
    }

    fun addWord(word: Word) {
        val id = word.id ?: UUID.randomUUID().toString()
        db.update("insert into words values ( ?, ?, ?, ? )", id, word.content, word.lastTimeUsed, word.type)
    }

    fun editWord(word: Word) {
        db.update("update words set last_time_used = (?) where id = (?)", word.lastTimeUsed, word.id)
    }

    fun noteWordUsage(wordId: String) {
        db.update("update words set last_time_used = (?) where id = (?)", LocalDateTime.now(), wordId)
    }
}