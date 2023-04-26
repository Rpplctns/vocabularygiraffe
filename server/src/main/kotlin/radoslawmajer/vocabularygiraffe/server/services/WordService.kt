package radoslawmajer.vocabularygiraffe.server.services

import org.springframework.stereotype.Service
import radoslawmajer.vocabularygiraffe.server.data.Word
import radoslawmajer.vocabularygiraffe.server.repository.WordRepository
import radoslawmajer.vocabularygiraffe.server.utils.getType
import java.io.FileNotFoundException
import java.time.LocalDateTime
import java.util.*

@Service
class WordService (var wordRepository: WordRepository) {

    /**
     * Returns list of all user's words.
     * @return list of words
     */
    fun getWords(): MutableIterable<Word> = wordRepository.findAll()

    /**
     * Adds word to database.
     * @param wordContent Word
     * @throws FileNotFoundException if there is no such word in the dictionary
     */
    @Throws(FileNotFoundException::class)
    fun addWord(wordContent: String, category: Int) {
        val time = LocalDateTime.now()
        val type = getType(wordContent)
        val word = Word(content = wordContent, type = type, status = 0, category = category, used = time)
        wordRepository.save(word)

    }

    fun deleteWord(id: UUID) {
        wordRepository.deleteById(id)
    }

    fun setCategory(id: UUID, category: Int) {
        val record: Optional<Word> = wordRepository.findById(id)
        val word = record.get()
        word.category = category
        wordRepository.save(word)
    }
}