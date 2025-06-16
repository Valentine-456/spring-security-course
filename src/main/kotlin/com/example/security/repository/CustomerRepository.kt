package com.example.security.repository

import com.example.security.model.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface CustomerRepository : CrudRepository<Customer, Long> {
    fun findByEmail(email: String): Optional<Customer>
}