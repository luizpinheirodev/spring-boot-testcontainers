package dev.backend.luiz.springboottestcontainers

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootTestContainersApplication

fun main(args: Array<String>) {
    runApplication<SpringBootTestContainersApplication>(*args)
}
