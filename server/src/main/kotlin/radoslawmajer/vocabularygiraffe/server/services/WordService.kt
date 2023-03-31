package radoslawmajer.vocabularygiraffe.server.services

import radoslawmajer.vocabularygiraffe.server.database.*
import radoslawmajer.vocabularygiraffe.server.utils.*

import org.springframework.stereotype.Service
import org.springframework.jdbc.core.JdbcTemplate
import java.time.LocalDateTime
import java.util.*

@Service
class WordService(val db: JdbcTemplate) {

    /**
     * Returns list of all user's words.
     * @return list of words
     */
    fun getWords(): List<Word> = db.query("select * from words") { response, _ ->
        Word(
            response.getString("id"),
            response.getString("content"),
            response.getTimestamp("last_time_used"),
            response.getString("word_type")
        )
    }

    /**
     * Adds word to database.
     * @param word Word
     */
    fun addWord(word: Word) {
        val id = word.id ?: UUID.randomUUID().toString()
        val type = word.content?.let { getType(it) }
        db.update("insert into words values ( ?, ?, ?, ? )", id, word.content, word.lastTimeUsed, type)
    }
}