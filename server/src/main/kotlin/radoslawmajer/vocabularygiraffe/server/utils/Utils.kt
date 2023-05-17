package radoslawmajer.vocabularygiraffe.server.utils

import com.aallam.openai.client.OpenAI
import com.google.gson.JsonParser
import java.io.FileNotFoundException
import java.net.URL

/**
 * Gets a sentence containing the keyword in square brackets.
 * @param word a keyword
 * @param openAI an openai client
 * @return a result sentence
 */
suspend fun getSentence(word: String, openAI: OpenAI): String = "I suppose $word is the word youre lookin for!"
//openAI.completion(
//    CompletionRequest(
//        model = ModelId("text-davinci-003"),
//        prompt = "Give an example of sentence with word \"$word\", putting the keyword in square brackets.",
//        temperature = 1.0
//    )
//).choices[0].text


/**
 * Returns string of parts of speech, which the given word can be.
 * Uses a dictionaryapi website.
 * @param word a given word
 * @return a string of parts of speech
 * @throws FileNotFoundException if there is no such word in a dictionary
 */
@Throws(FileNotFoundException::class)
fun getType(word: String): String = JsonParser
    .parseString(
        URL("https://api.dictionaryapi.dev/api/v2/entries/en/$word")
            .readText()
    )
    .asJsonArray
    .get(0)
    .asJsonObject
    .get("meanings")
    .asJsonArray
    .map { q ->
        q.asJsonObject
            .get("partOfSpeech")
            .asString
    }
    .distinct()
    .joinToString(separator = "/") { q ->
        when (q) {
            "adjective" -> "adj."
            "noun" -> "n."
            "verb" -> "v."
            "adverb" -> "adv."
            "pronoun" -> "pron."
            "preposition" -> "prep."
            "conjunction" -> "conj."
            "interjection" -> "interj."
            else -> q
        }
    }
