package radoslawmajer.vocabularygiraffe.server.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import radoslawmajer.vocabularygiraffe.server.data.*
import radoslawmajer.vocabularygiraffe.server.services.*
import radoslawmajer.vocabularygiraffe.server.utils.*
import java.io.FileNotFoundException
import java.util.*

@RestController
@RequestMapping("/api/words")
class WordsController (val service: WordService) {
    @GetMapping("/")
    fun getAllWords(): ResponseEntity<MutableIterable<Word>> = ResponseEntity.ok(service.getWords())

    @PostMapping("/")
    fun addWord(@RequestParam("word") word: String, @RequestParam("category") category: Int): ResponseEntity<Boolean> {
        return try {
            service.addWord(word, category)
            ResponseEntity.ok(true)
        } catch (e: FileNotFoundException) {
            ResponseEntity.ok(false)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteWord(@PathVariable("id") id: UUID) {
        service.deleteWord(id)
    }

    @PutMapping("/{id}")
    fun setCategory(@PathVariable("id") id: UUID, category: Int) {
        service.setCategory(id, category)
    }
}