package radoslawmajer.vocabularygiraffe.server

import com.aallam.openai.client.OpenAI
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

val KNOWN_ERRORS: Array<String> = arrayOf(
    "Incorrect password",
    "User does not exist",
    "User already exists",
    "Username is too long",
    "Username must not be empty",
    "Username must contain only alphabet letters and numbers",
    "Password is too long",
    "Password is too short",
    "Password must not be empty",
    "Password must contain at least one small letter, one capital letter and one number"
)
const val SECRET_KEY = "404D635166546A57"

@Configuration
class Config : WebMvcConfigurer {
    /**
     * OpenAI client bean. Uses API key from environmental variable.
     */
    @Bean
    fun openAI (): OpenAI = OpenAI(System.getenv("OPENAI-API-KEY"))

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .maxAge(3600)
    }
}
