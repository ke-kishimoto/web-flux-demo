package com.example.reactivesystem.customer

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class CustomerController {

    @GetMapping("/customer")
    fun index(): String {
        // Thymeleaf テンプレートを返す
        return "customer"
    }
}