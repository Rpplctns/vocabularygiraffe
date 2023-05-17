package radoslawmajer.vocabularygiraffe.server.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import radoslawmajer.vocabularygiraffe.server.utils.JwtAuthentication

@Configuration
@EnableWebSecurity
class SecurityConfig(private val jwtAuthentication: JwtAuthentication, private val authenticationProvider: AuthenticationProvider) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf().disable().authorizeHttpRequests()
            .requestMatchers("").permitAll()
            .anyRequest().authenticated()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthentication, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }
}