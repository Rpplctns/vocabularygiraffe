package radoslawmajer.vocabularygiraffe.server.api

import org.springframework.web.bind.annotation.*
import radoslawmajer.vocabularygiraffe.server.database.Word
import radoslawmajer.vocabularygiraffe.server.services.WordService

@RestController
@RequestMapping("/api/words")
class WordsController (val service: WordService) {
    @GetMapping("/")
    fun getAllWords(): List<Word> = service.getWords()

    @GetMapping("/use/{id}")
    fun useWord(@PathVariable id:String) {
        service.noteWordUsage(id)
    }

    @PostMapping("/")
    fun addWord(@RequestBody word: Word) {
        service.addWord(word)
    }

    @PutMapping("/")
    fun editWord(@RequestBody word: Word) {
        service.editWord(word)
    }
}