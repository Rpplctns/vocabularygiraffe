package radoslawmajer.vocabularygiraffe.server.services

import radoslawmajer.vocabularygiraffe.server.database.*
import radoslawmajer.vocabularygiraffe.server.utils.*

import org.springframework.stereotype.Service
import org.springframework.jdbc.core.JdbcTemplate
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
            response.getString("word_type"),
            response.getInt("status")
        )
    }

    /**
     * Adds word to database.
     * @param word Word
     */
    fun addWord(word: Word) {
        val id = UUID.randomUUID().toString()
        val type = getType(word.content)
        db.update("insert into words values ( ?, ?, ?, ?, ? )", id, word.content, null, type, 0)
    }
}