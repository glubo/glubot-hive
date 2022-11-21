package cz.glubo.glubothive
// TODO: remove this temporary playground

import kotlinx.coroutines.delay
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class HelloController {
    @GetMapping("/hello")
    suspend fun hello(principal: Principal): Hello {
        delay(1000)
        return Hello(principal.name)
    }

    @GetMapping("/sudo/hello")
    suspend fun sudoHello() = Hello()

    data class Hello(
        val hello: String = "World!",
    )

}

@Configuration
@EnableWebFluxSecurity
class HelloWebSecurityConfig {
    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http
            .authorizeExchange { exchanges ->
                exchanges
                    .pathMatchers("/sudo/**").permitAll()
                    .anyExchange().authenticated()
            }.csrf().disable()
            .oauth2ResourceServer { it.jwt() }
        return http.build()
    }
}
