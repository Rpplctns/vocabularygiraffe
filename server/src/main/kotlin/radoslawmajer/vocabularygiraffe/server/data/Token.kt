package radoslawmajer.vocabularygiraffe.server.data

import radoslawmajer.vocabularygiraffe.server.SECRET_KEY
import java.time.LocalDateTime
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

data class Token(
    var id: UUID,
    var expiration: LocalDateTime
) {
    fun isValid(): Boolean = expiration.isAfter(LocalDateTime.now())

    override fun toString(): String = "TOKEN::$id::$expiration"

    fun encrypt(): String {
        val c = Cipher.getInstance("AES/ECB/PKCS5Padding")
        c.init(Cipher.ENCRYPT_MODE, SecretKeySpec(SECRET_KEY.toByteArray(), "AES"))
        return Base64.getEncoder().encodeToString(c.doFinal(this.toString().toByteArray()))
            .replace("/", "_")
            .replace("+", "-")
            .replace("=", "")
    }
}

@Throws(Exception::class)
private fun obtainFromString(token: String): Token {
    val l = token.split("::")
    if (l[0] != "TOKEN") throw Exception("Invalid token")
    return Token(UUID.fromString(l[1]), LocalDateTime.parse(l[2]))
}

fun decrypt(encryptedString: String): Token {
    val c = Cipher.getInstance("AES/ECB/PKCS5Padding")
    c.init(Cipher.DECRYPT_MODE, SecretKeySpec(SECRET_KEY.toByteArray(), "AES"))
    val s = String(c.doFinal(Base64.getDecoder().decode(
        encryptedString
            .replace("_", "/")
            .replace("-", "+")
            .padEnd(encryptedString.length + 4 - (encryptedString.length % 4), '=')
    )))
    return obtainFromString(s)
}

@Throws(Exception::class)
fun validateAndGetId(token: String): UUID {
    val t = decrypt(token)
    if(!t.isValid()) throw Exception("Invalid token")
    else return t.id
}

fun generate(user: UUID): Token = Token(user, LocalDateTime.now().plusMinutes(30))
