package radoslawmajer.vocabularygiraffe.server.services

import com.aallam.openai.client.OpenAI
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import radoslawmajer.vocabularygiraffe.server.data.Exercise
import radoslawmajer.vocabularygiraffe.server.data.Quiz
import radoslawmajer.vocabularygiraffe.server.repository.WordRepository
import radoslawmajer.vocabularygiraffe.server.utils.getSentence
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
            runBlocking{ Exercise(w.id, getSentence(w.content!!, openAI), w.content, w.type) }
        }
    )
}