package com.example.reactivesystem.customer

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration

@RestController
class CustomerController(
    private val customerRepository: CustomerRepository
) {
    @GetMapping("/customers")
    fun getAllCustomers(): Flux<Customer> {
        return customerRepository.findAll()
//            .buffer(100) // 3件ずつまとめて
//            .delayElements(Duration.ofMillis(500)) // 1秒ごとに
//            .flatMapIterable { it } // 再度1件ずつ流す
    }
}