package com.example.reactivesystem.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class NonReactiveRestController {

    @GetMapping("/demo/non-reactive")
    fun nonReactiveEndpoint(): ResponseEntity<String> {
        val result = "Hello"
        // レスポンスを返す
        return ResponseEntity.ok(result)
    }
}