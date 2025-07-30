package com.example.reactivesystem.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration

@RestController
class ReactiveRestController {

    @GetMapping("/demo/sse")
    fun reactiveEndpoint1(): Flux<String> {
        val message = "Hello! This is a Server-sent Events example."
        val res = Flux.interval(Duration.ofMillis(50)) // 50ミリ秒ごとに発行
            .take(message.length.toLong()) // メッセージの長さ分
            .map { it -> message[it.toInt()].toString() } // 各文字を1つずつ
        return res
    }
}