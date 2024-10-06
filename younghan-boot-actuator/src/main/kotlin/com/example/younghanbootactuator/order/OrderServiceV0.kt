package com.example.younghanbootactuator.order

import java.util.concurrent.atomic.AtomicInteger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

private val log = LoggerFactory.getLogger(OrderServiceV0::class.java)

@Service
class OrderServiceV0 : OrderService {

    private val stock = AtomicInteger(100)

    override fun order() {
        log.info("주문")
        stock.decrementAndGet()
    }

    override fun cancel() {
        log.info("취소")
        stock.incrementAndGet()
    }

    override fun getStock(): AtomicInteger {
        return stock
    }
}