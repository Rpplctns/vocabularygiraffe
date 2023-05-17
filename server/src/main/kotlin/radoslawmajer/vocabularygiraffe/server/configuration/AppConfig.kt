package radoslawmajer.vocabularygiraffe.server.configuration

import com.aallam.openai.client.OpenAI
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import radoslawmajer.vocabularygiraffe.server.repository.UserRepository

@Configuration
class AppConfig (val userRepository: UserRepository) {
    /**
     * OpenAI client bean. Uses API key from environmental variable.
     */
    @Bean
    fun openAI (): OpenAI = OpenAI(System.getenv("OPENAI-API-KEY"))

    @Bean
    fun userDetailService(): UserDetailsService = UserDetailsService {
            username -> userRepository.findByEmail(username)
                .orElseThrow{throw UsernameNotFoundException("user not found") }
    }

    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val authenticationProvider = DaoAuthenticationProvider()
        authenticationProvider.setUserDetailsService(userDetailService())
        authenticationProvider.setPasswordEncoder(BCryptPasswordEncoder())
        return authenticationProvider
    }
}
