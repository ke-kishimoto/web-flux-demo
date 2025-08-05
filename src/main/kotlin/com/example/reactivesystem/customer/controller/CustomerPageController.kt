package com.example.reactivesystem.customer.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class CustomerPageController {

    @GetMapping("/customer")
    fun index(): String {
        return "customer"
    }
}