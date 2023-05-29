package radoslawmajer.vocabularygiraffe.server.services

import com.aallam.openai.client.OpenAI
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import radoslawmajer.vocabularygiraffe.server.data.Quiz
import radoslawmajer.vocabularygiraffe.server.data.Results
import radoslawmajer.vocabularygiraffe.server.repository.WordRepository
import radoslawmajer.vocabularygiraffe.server.utils.getSentence
import java.time.LocalDateTime
import java.util.*


@Service
class QuizService(var wordRepository: WordRepository) {

    @Autowired
    private lateinit var openAI: OpenAI

    /**
     * Generates quiz.
     * @return quiz
     */
    fun getQuiz(user: UUID): Quiz = Quiz(
        wordRepository.quizWordSet(user).map { w ->
            runBlocking{ Pair(w, getSentence(w.content!!, openAI)) }
        }
    )

    /**
     * Process results of the quiz. Updates the date of giving the exercise last time
     * @param results results of the quiz
     */
    fun acceptResults(results: Results, user: UUID) {
        val time = LocalDateTime.now()
        for (r in results.exercises) {
            val record = wordRepository.findById(r.first)
            val word = record.get()
            word.used = time
            word.status = word.status!! * 3 + if(r.second) 2 else 1
            if(word.status!! >= 243) word.status = word.status!! - 243
            if(word.status == 242) word.category = word.category!! + 1
            wordRepository.save(word)
        }
    }
}