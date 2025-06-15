package com.example.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.password.CompromisedPasswordChecker
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker

@Configuration
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { auth ->
            auth.requestMatchers(
                    "/myAccount", "/myCards", "/myBalance", "/myLoans"
                ).authenticated()
                .requestMatchers(
                    "/notices", "/contact", "/error"
                ).permitAll()
        }
            .formLogin(withDefaults())
            .httpBasic (withDefaults())
        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val user = User
            .withUsername("user")
            .password("{noop}password")
            .authorities("read")
            .build()
        val admin = User
            .withUsername("admin")
            .password("{bcrypt}${BCryptPasswordEncoder().encode("voy.a.mexico")}")
            .authorities("admin")
            .build()
        return InMemoryUserDetailsManager(user, admin)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    fun compromisedPasswordChecker(): CompromisedPasswordChecker {
        return HaveIBeenPwnedRestApiPasswordChecker();
    }
}