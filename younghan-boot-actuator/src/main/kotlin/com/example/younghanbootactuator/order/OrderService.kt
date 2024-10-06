package com.example.younghanbootactuator.order

import java.util.concurrent.atomic.AtomicInteger

interface OrderService {
    fun order()
    fun cancel()
    fun getStock(): AtomicInteger
}