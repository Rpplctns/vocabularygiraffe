package radoslawmajer.vocabularygiraffe.server

import com.aallam.openai.client.OpenAI
import org.springframework.context.annotation.*
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
@ComponentScan("radoslawmajer.vocabularygiraffe.server.utils")
class Config : WebMvcConfigurer {

    /**
     * OpenAI client bean. Uses API key from environmental variable.
     */
    @Bean
    fun openAI (): OpenAI = OpenAI(System.getenv("OPENAI-API-KEY"))

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedMethods("*")
    }
}
