package com.example.reactivesystem.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration

@RestController
class ReactiveRestController {

    @GetMapping("/demo/reactive-1")
    fun reactiveEndpoint1(): Flux<String> {
        // 非同期処理の例
        val message = "Hello"
        val res = Flux.interval(Duration.ofMillis(500))
            .take(5)                    // 5 回だけ
            .map { it -> message[it.toInt()].toString() }
        return res
    }
}