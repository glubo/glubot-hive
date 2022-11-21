package cz.glubo.glubothive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GlubotHiveApplication

fun main(args: Array<String>) {
    runApplication<GlubotHiveApplication>(*args)
}
