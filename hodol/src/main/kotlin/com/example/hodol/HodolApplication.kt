package com.example.hodol

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HodolApplication

fun main(args: Array<String>) {
    runApplication<HodolApplication>(*args)
}
