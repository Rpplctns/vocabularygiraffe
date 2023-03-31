package radoslawmajer.vocabularygiraffe.server.api

import org.springframework.web.bind.annotation.*
import radoslawmajer.vocabularygiraffe.server.database.*
import radoslawmajer.vocabularygiraffe.server.services.*
import radoslawmajer.vocabularygiraffe.server.utils.*

@RestController
@RequestMapping("/api/words")
class WordsController (val service: WordService) {
    @GetMapping("/")
    fun getAllWords(): List<Word> = service.getWords()

    @PostMapping("/")
    fun addWord(@RequestBody word: Word) {
        service.addWord(word)
    }
}