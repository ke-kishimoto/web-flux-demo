package com.example.reactivesystem.customer

import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface CustomerRepository : ReactiveCrudRepository<Customer, Int> {
    // 特に追加メソッドがなければそのままでOK
}