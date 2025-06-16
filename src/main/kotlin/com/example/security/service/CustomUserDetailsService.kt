package com.example.security.service

import com.example.security.model.Customer
import com.example.security.repository.CustomerRepository
import org.springframework.security.authorization.AuthoritiesAuthorizationManager
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    val customerRepository: CustomerRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails? {
        val customer = customerRepository
            .findByEmail(username)
            .orElseThrow { throw UsernameNotFoundException("User details not found for the user: $username") }
        val authorities = listOf<GrantedAuthority>(SimpleGrantedAuthority(customer.role))
        return User(customer.email, customer.pwd, authorities)
    }

}
