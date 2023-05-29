package radoslawmajer.vocabularygiraffe.server.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import radoslawmajer.vocabularygiraffe.server.data.Word
import java.util.*

@Repository
interface WordRepository : JpaRepository<Word, UUID> {

    @Query("SELECT w FROM Word w WHERE w.category = 0 AND w.user = :#{#user} ORDER BY w.used LIMIT 10")
    fun quizWordSet(@Param("user") user: UUID): List<Word>
}