package radoslawmajer.vocabularygiraffe.server.services

import org.springframework.data.domain.Example
import org.springframework.stereotype.Service
import radoslawmajer.vocabularygiraffe.server.data.Word
import radoslawmajer.vocabularygiraffe.server.repository.WordRepository
import radoslawmajer.vocabularygiraffe.server.utils.getType
import java.io.FileNotFoundException
import java.time.LocalDateTime
import java.util.*

@Service
class WordService (var repository: WordRepository) {

    /**
     * Returns list of all words.
     * @return list of words
     */
    fun getWords(): MutableIterable<Word> = repository.findAll()

    /**
     * Returns list of all user's words.
     * @return list of words
     */
    fun getWords(user: UUID): MutableIterable<Word> =
        repository.findAll(Example.of(Word(null, null, null, null, null, user)))

    /**
     * Adds word to database.
     * @param wordContent Word
     * @throws FileNotFoundException if there is no such word in the dictionary
     */
    @Throws(FileNotFoundException::class)
    fun addWord(wordContent: String, category: Int, user: UUID) {
        val time = LocalDateTime.now()
        val type = getType(wordContent)
        val word = Word(content = wordContent, type = type, category = category, used = time, user = user)
        repository.save(word)
    }

    @Throws(Exception::class)
    fun deleteWord(id: UUID, user: UUID) {
        val wordRecord = repository.findById(id)
        if(wordRecord.isEmpty) throw Exception("Word does not exist")
        if(wordRecord.get().user != user) throw Exception("Unauthorised operation")
        repository.delete(wordRecord.get())
    }

    fun setCategory(id: UUID, category: Int, user: UUID) {
        val wordRecord = repository.findById(id)
        if(wordRecord.isEmpty) throw Exception("Word does not exist")
        if(wordRecord.get().user != user) throw Exception("Unauthorised operation")
        wordRecord.get().category = category
        repository.save(wordRecord.get())
    }
}