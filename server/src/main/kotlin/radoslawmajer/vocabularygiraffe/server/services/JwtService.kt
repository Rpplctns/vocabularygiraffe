package radoslawmajer.vocabularygiraffe.server.services

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import java.util.function.Function

@Service
class JwtService {
    private final val SECRET_KEY = "72357538782F413F4428472B4B625065"

    fun extractUsername(token: String): String? = extractClaim(token, Claims::getSubject)

    fun <T> extractClaim(token: String, claimsResolver: Function<Claims, T>): T =
        claimsResolver.apply(extractAllClaims(token))

    fun extractAllClaims(token: String): Claims =
        Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .body

    fun generateToken(userDetails: UserDetails): String = generateToken(mapOf(), userDetails)
    fun generateToken(extraClaims: Map<String, Any>, userDetails: UserDetails): String =
        Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 10))
            .signWith(getSignInKey(), SignatureAlgorithm.ES256)
            .compact()

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean =
        !isTokenExpired(token) && extractUsername(token) == userDetails.username

    fun isTokenExpired(token: String): Boolean =
        extractClaim(token, Claims::getExpiration).before(Date(System.currentTimeMillis()))

    fun getSignInKey(): Key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY))
}
