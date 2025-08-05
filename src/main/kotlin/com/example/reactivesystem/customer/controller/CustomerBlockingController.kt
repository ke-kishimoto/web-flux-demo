package com.example.reactivesystem.customer.controller

import com.example.reactivesystem.customer.jpa.CustomerJpa
import com.example.reactivesystem.customer.jpa.CustomerJpaRepository
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.concurrent.CompletableFuture

@RestController
class CustomerBlockingController(
    private val customerJpaRepository: CustomerJpaRepository
) {

    @GetMapping("/customers/blocking")
    fun getAllCustomersBlocking(): SseEmitter {
        val emitter = SseEmitter(Long.MAX_VALUE)

        // 非同期でブロッキング処理を実行（メインスレッドをブロックしないため）
        CompletableFuture.runAsync {
            try {
                // ブロッキングでDBからデータを取得
                val customers = customerJpaRepository.findAll()

                customers.forEach { customer ->
                    try {
                        // JSONデータとしてSSEで送信（遅延なし）
                        emitter.send(
                            SseEmitter.event()
                                .data(customer, MediaType.APPLICATION_JSON)
                        )
                    } catch (e: Exception) {
                        emitter.completeWithError(e)
                        return@runAsync
                    }
                }
                emitter.complete()
            } catch (e: Exception) {
                emitter.completeWithError(e)
            }
        }

        return emitter
    }

    @GetMapping("/customers/blocking/bulk", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllCustomersBulk(): List<CustomerJpa> {
        // ブロッキングで全データを一括取得・返却
        return customerJpaRepository.findAll()
    }
}