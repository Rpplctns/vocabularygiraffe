package radoslawmajer.vocabularygiraffe.server.api

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
    @ResponseBody
    fun getAllWords(): MutableIterable<Word> = service.getWords()

    @PostMapping("/")
    @ResponseBody
    fun addWord(@RequestParam("word") word: String, @RequestParam("category") category: Int): Boolean {
        return try {
            service.addWord(word, category)
            true
        } catch (e: FileNotFoundException) {
            false
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