package com.example.security.controller

import com.example.security.model.Customer
import com.example.security.repository.CustomerRepository
import com.example.security.service.CustomUserDetailsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val customUserDetailsService: CustomUserDetailsService,
    private val passwordEncoder : PasswordEncoder, private val customerRepository: CustomerRepository
) {
    @PostMapping("/register")
    fun registerUser(@RequestBody customer: Customer): ResponseEntity<String> {
        try {
            val pwdHash = passwordEncoder.encode(customer.pwd)
            customer.pwd = pwdHash
            var savedCustomer = customerRepository.save(customer)
            savedCustomer.id?.let { if (it > 0) {
                return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Successfully registered a user")
            } }
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Didn't pass validation")
        } catch (exception: Exception) {
            return ResponseEntity(exception.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
