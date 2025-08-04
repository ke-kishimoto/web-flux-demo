package com.example.reactivesystem.customer

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class CustomerPageController {
    
    @GetMapping("/customer-demo")
    fun customerDemo(): String {
        return "customer"
    }
}
