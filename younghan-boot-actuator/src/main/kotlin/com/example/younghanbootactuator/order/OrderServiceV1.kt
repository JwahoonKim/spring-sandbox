package com.example.younghanbootactuator.order

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import java.util.concurrent.atomic.AtomicInteger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service

private val log = LoggerFactory.getLogger(OrderServiceV1::class.java)

@Primary
@Service
class OrderServiceV1(
    private val meterRegistry: MeterRegistry,
) : OrderService {
    private val stock = AtomicInteger(100)

    override fun order() {
        log.info("주문")
        stock.decrementAndGet()

        Counter.builder("my.order")
            .tag("class", javaClass.simpleName)
            .tag("method", "order")
            .description("order")
            .register(meterRegistry).increment()
    }

    override fun cancel() {
        log.info("취소")
        stock.incrementAndGet()

        Counter.builder("my.order")
            .tag("class", javaClass.simpleName)
            .tag("method", "cancel")
            .description("cancel")
            .register(meterRegistry).increment()
    }

    override fun getStock(): AtomicInteger {
        return stock
    }
}