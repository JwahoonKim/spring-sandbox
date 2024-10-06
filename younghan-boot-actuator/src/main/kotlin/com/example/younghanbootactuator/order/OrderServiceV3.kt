package com.example.younghanbootactuator.order

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import java.util.concurrent.atomic.AtomicInteger
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

private val log = LoggerFactory.getLogger(OrderServiceV3::class.java)

@Primary
@Service
class OrderServiceV3 : OrderService {
    private val stock = AtomicInteger(100)

    @MyCounted("my.order")
    override fun order() {
        log.info("주문")
        stock.decrementAndGet()
    }

    @MyCounted("my.order")
    override fun cancel() {
        log.info("취소")
        stock.incrementAndGet()
    }

    override fun getStock(): AtomicInteger {
        return stock
    }
}

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class MyCounted(val value: String)

@Aspect
@Component
class CounterAspect(
    private val registry: MeterRegistry
) {

    @Around("@annotation(MyCounted)")
    fun count(joinPoint: ProceedingJoinPoint): Any? {
        val result = joinPoint.proceed()
        val signature = joinPoint.signature
        val className = signature.declaringType.simpleName
        val methodName = signature.name

        Counter.builder("my.order")
            .tag("class", className)
            .tag("method", methodName)
            .description("order")
            .register(registry).increment()

        return result
    }

}