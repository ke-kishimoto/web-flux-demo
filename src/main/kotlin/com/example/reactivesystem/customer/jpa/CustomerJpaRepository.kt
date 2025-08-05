package com.example.reactivesystem.customer.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerJpaRepository : JpaRepository<CustomerJpa, Int> {
    // 標準的なCRUD操作はJpaRepositoryから継承
}