package com.example.reactivesystem.customer

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("customer")
data class Customer(
    @Id
    val id: Int,
    val name: String
)