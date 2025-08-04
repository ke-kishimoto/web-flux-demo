package com.example.reactivesystem.customer

import jakarta.persistence.*

@Entity
@Table(name = "customer")
data class CustomerJpa(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    
    @Column(name = "name")
    val name: String = ""
) {
    // JPAのため、引数なしコンストラクタが必要
    constructor() : this(0, "")
}
