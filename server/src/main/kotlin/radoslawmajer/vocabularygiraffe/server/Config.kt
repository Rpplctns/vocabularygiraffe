package radoslawmajer.vocabularygiraffe.server

import com.aallam.openai.client.OpenAI
import org.springframework.context.annotation.*

@Configuration
@ComponentScan("radoslawmajer.vocabularygiraffe.server.utils")
class Config {

    /**
     * OpenAI client bean. Uses API key from environmental variable.
     */
    @Bean
    fun openAI (): OpenAI = OpenAI(System.getenv("OPENAI-API-KEY"))
}