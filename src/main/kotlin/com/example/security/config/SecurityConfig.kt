package com.example.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.password.CompromisedPasswordChecker
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker
import javax.sql.DataSource

@Configuration
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { auth ->
            auth.requestMatchers(
                "/myAccount", "/myCards", "/myBalance", "/myLoans"
            ).authenticated().requestMatchers(
                    "/notices", "/contact", "/error", "/register"
                ).permitAll()
        }
            .formLogin(withDefaults())
            .httpBasic(withDefaults())
            .csrf { it.disable() }
        return http.build()
    }

//    @Bean
//    fun userDetailsService(datasource: DataSource): UserDetailsService {
//        return JdbcUserDetailsManager(datasource)
//    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

//    @Bean
    fun compromisedPasswordChecker(): CompromisedPasswordChecker {
        return HaveIBeenPwnedRestApiPasswordChecker();
    }
}