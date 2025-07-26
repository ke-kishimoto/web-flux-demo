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
import java.util.logging.Level

@RestController
class DemoRestController(private val builder: WebClient.Builder) {

    private val sseType =
        object : ParameterizedTypeReference<ServerSentEvent<String>>() {}

    // ストリーミング: Flux
    @GetMapping("/demo/stream", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun stream(): Flux<String> =
        Flux.interval(Duration.ofMillis(500))
            .take(5)                    // 5 回だけ
            .map { "$it" }         // 0,1,2,3,4 を文字列化


    private val client = builder
        .baseUrl("http://localhost:8081") // 外部 SSE サーバーの URL
        .defaultHeader(HttpHeaders.ACCEPT, MediaType.TEXT_EVENT_STREAM_VALUE)
        .build()

    /** 外部 SSE → Flux<String> へ */
    @GetMapping(value = ["/demo/delay"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun streamChat(): Flux<String> {
        return client.get()
            .uri("/api/delay/demo")
            .accept(MediaType.TEXT_EVENT_STREAM)
            .retrieve()
            .bodyToFlux(sseType)                 // Flux<ServerSentEvent<String>>
            .filter { it.data() != null }        // null を除外
            .map    { it.data()!! }              // 非 null へ変換
            .log("chat.data")
    }

}