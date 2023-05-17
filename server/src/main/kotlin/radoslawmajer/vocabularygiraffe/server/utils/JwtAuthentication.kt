package radoslawmajer.vocabularygiraffe.server.utils

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import radoslawmajer.vocabularygiraffe.server.services.JwtService

@Component
class JwtAuthentication(val jwtService: JwtService, val userDetailService: UserDetailsService) :
    OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        var token = request.getHeader("Authorization")
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7)
            val username = jwtService.extractUsername(token)
            if (username != null && SecurityContextHolder.getContext().authentication == null) {
                val userDetails = userDetailService.loadUserByUsername(username)
                if (jwtService.isTokenValid(token, userDetails)) {
                    var authToken = UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        null
                    )
                    authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication = authToken
                }
            }
        }
        filterChain.doFilter(request, response)
    }
}