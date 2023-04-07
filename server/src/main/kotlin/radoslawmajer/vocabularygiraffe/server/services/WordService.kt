package radoslawmajer.vocabularygiraffe.server.services

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import radoslawmajer.vocabularygiraffe.server.database.Word
import radoslawmajer.vocabularygiraffe.server.utils.getType
import java.io.FileNotFoundException
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
            response.getString("word_type"),
            response.getInt("status")
        )
    }

    /**
     * Adds word to database.
     * @param word Word
     * @throws FileNotFoundException if there is no such word in the dictionary
     */
    @Throws(FileNotFoundException::class)
    fun addWord(word: String) {
        val id = UUID.randomUUID().toString()
        val type = getType(word)
        db.update("insert into words values ( ?, ?, ?, ?, ? )", id, word, null, type, 0)
    }
}