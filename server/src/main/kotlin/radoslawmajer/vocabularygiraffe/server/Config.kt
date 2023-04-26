package radoslawmajer.vocabularygiraffe.server

import com.aallam.openai.client.OpenAI
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class Config : WebMvcConfigurer {
    /**
     * OpenAI client bean. Uses API key from environmental variable.
     */
    @Bean
    fun openAI (): OpenAI = OpenAI(System.getenv("OPENAI-API-KEY"))
}
