package com.example.reactivesystem.customer.controller

import com.example.reactivesystem.customer.Customer
import com.example.reactivesystem.customer.CustomerRepository
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class CustomerRestController(
    private val customerRepository: CustomerRepository
) {
    @GetMapping("/customers/reactive/bulk", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun getAllCustomersBulk(): Flux<Customer> {
        // リアクティブでSSEストリーミング（遅延なし）
        return customerRepository.findAll()
    }
}