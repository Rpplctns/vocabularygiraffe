package radoslawmajer.vocabularygiraffe.server.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import radoslawmajer.vocabularygiraffe.server.data.*
import radoslawmajer.vocabularygiraffe.server.services.*
import radoslawmajer.vocabularygiraffe.server.utils.*
import java.util.*

@RestController
@RequestMapping("/api/words")
class WordController (val service: WordService) {
    @GetMapping("/")
    fun getWords(@RequestParam("token") token: String): ResponseEntity<MutableIterable<Word>> =
        ResponseEntity.ok(service.getWords(validateAndGetId(token)))

    @PostMapping("/")
    fun addWord(@RequestParam("word") word: String, @RequestParam("category") category: Int, @RequestParam("token") token: String) =
        service.addWord(word, category, validateAndGetId(token))

    @DeleteMapping("/{id}")
    fun deleteWord(@PathVariable("id") id: UUID, @RequestParam("token") token: String) =
        service.deleteWord(id, validateAndGetId(token))

    @PutMapping("/{id}")
    fun setCategory(@PathVariable("id") id: UUID, category: Int, @RequestParam("token") token: String) =
        service.setCategory(id, category, validateAndGetId(token))
}