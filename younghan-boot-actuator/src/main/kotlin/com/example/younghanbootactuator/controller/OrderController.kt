package com.example.younghanbootactuator.controller

import com.example.younghanbootactuator.order.OrderService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

private val log = LoggerFactory.getLogger(OrderController::class.java)

@RestController
class OrderController(
    private val orderService: OrderService
) {

    @GetMapping("/order")
    fun order(): String {
        log.info("order")
        orderService.order()
        return "order"
    }

    @GetMapping("/cancel")
    fun cancel(): String {
        log.info("cancel")
        orderService.cancel()
        return "cancel"
    }

    @GetMapping("/stock")
    fun stock(): String {
        log.info("stock")
        return "stock: ${orderService.getStock().get()}"
    }

}