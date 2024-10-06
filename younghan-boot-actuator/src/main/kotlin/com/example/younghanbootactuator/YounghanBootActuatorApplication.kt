package com.example.younghanbootactuator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
class YounghanBootActuatorApplication

fun main(args: Array<String>) {
    runApplication<YounghanBootActuatorApplication>(*args)
}
