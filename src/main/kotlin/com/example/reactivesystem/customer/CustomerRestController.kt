package com.example.reactivesystem.customer

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class CustomerRestController(
    private val customerRepository: CustomerRepository
) {
    @GetMapping("/customers", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun getAllCustomers(): Flux<Customer> {
        return customerRepository.findAll()
            // 遅延を削除してパフォーマンス比較
    }
    
    @GetMapping("/customers/reactive/bulk", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun getAllCustomersBulk(): Flux<Customer> {
        // リアクティブでSSEストリーミング（遅延なし）
        return customerRepository.findAll()
    }
}