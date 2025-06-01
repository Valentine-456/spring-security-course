package com.example.security.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController {

    @GetMapping("/myAccount")
    public fun getAccountDetails(): String  = "Account details from DB"
}