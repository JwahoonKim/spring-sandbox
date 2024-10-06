package com.example.younghanbootactuator.order

import io.micrometer.core.annotation.Counted
import io.micrometer.core.aop.CountedAspect
import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import java.util.concurrent.atomic.AtomicInteger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

private val log = LoggerFactory.getLogger(OrderServiceV2::class.java)

@Primary
@Service
class OrderServiceV2(
) : OrderService {
    private val stock = AtomicInteger(100)

    @Counted("my.order")
    override fun order() {
        log.info("주문")
        stock.decrementAndGet()
    }

    @Counted("my.order")
    override fun cancel() {
        log.info("취소")
        stock.incrementAndGet()
    }

    override fun getStock(): AtomicInteger {
        return stock
    }
}

@Configuration
class OrderServiceV2Configuration {

    @Bean
    fun countedAspect(registry: MeterRegistry): CountedAspect {
        return CountedAspect(registry)
    }
}