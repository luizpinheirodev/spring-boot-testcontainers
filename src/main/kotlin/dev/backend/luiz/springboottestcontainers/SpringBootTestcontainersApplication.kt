package dev.backend.luiz.springboottestcontainers

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootTestcontainersApplication

fun main(args: Array<String>) {
	runApplication<SpringBootTestcontainersApplication>(*args)
}
