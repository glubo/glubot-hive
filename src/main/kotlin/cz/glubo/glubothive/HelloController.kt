package cz.glubo.glubothive
// TODO: remove this temporary playground

import kotlinx.coroutines.delay
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


    data class Hello(
        val hello: String = "World!",
    )

}

