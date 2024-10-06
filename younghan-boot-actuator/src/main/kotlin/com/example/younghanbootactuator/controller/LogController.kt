package com.example.younghanbootactuator.controller

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

private val logger = LoggerFactory.getLogger(LogController::class.java)

@RestController
class LogController {

    @GetMapping("/logger")
    fun log(): String {
        logger.trace("trace log")
        logger.debug("debug log")
        logger.info("info log")
        logger.warn("warn log")
        logger.error("error log")
        return "ok"
    }

}