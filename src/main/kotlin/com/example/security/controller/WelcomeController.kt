package com.example.security.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WelcomeController {

    @GetMapping("/")
    public fun sayWelcome(): String  {
        return "Welcome to Spring Application without security"
    }
}