package radoslawmajer.vocabularygiraffe.server.api

import org.springframework.web.bind.annotation.*
import radoslawmajer.vocabularygiraffe.server.database.*
import radoslawmajer.vocabularygiraffe.server.services.*
import radoslawmajer.vocabularygiraffe.server.utils.*
import java.io.FileNotFoundException

@RestController
@RequestMapping("/api/words")
class WordsController (val service: WordService) {
    @GetMapping("/")
    fun getAllWords(): List<Word> = service.getWords()

    @PostMapping("/")
    fun addWord(@RequestParam("word") word: String): Boolean {
        return try {
            service.addWord(word)
            true
        } catch (e: FileNotFoundException) {
            false
        }
    }
}