package com.example.reactivesystem.controller

import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.codec.ServerSentEvent
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import java.time.Duration

@RestController
class ReactiveRestController(private val builder: WebClient.Builder) {

    private val sseType =
        object : ParameterizedTypeReference<ServerSentEvent<String>>() {}

    private val client = builder
        .baseUrl("http://localhost:8081") // Wiremockサーバー
        .defaultHeader(HttpHeaders.ACCEPT, MediaType.TEXT_EVENT_STREAM_VALUE)
        .build()

    @GetMapping("/demo/sse")
    fun streamChat(): Flux<String> {
        return client.get()
            .uri("/api/sse/demo")
            .accept(MediaType.TEXT_EVENT_STREAM)
            .retrieve()
            .bodyToFlux(sseType)                 // Flux<ServerSentEvent<String>>
            .filter { it.data() != null }        // null を除外
            .map    { it.data()!! }              // 非 null へ変換
            .log("chat.data")
    }

//    @GetMapping("/demo/sse")
//    fun reactiveEndpoint1(): Flux<String> {
//        val message = "Hello! This is a Server-sent Events example."
//        val res = Flux.interval(Duration.ofMillis(50)) // 50ミリ秒ごとに発行
//            .take(message.length.toLong()) // メッセージの長さ分
//            .map { it -> message[it.toInt()].toString() } // 各文字を1つずつ
//        return res
//    }
}