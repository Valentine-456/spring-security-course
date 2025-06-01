package com.example.security.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BalanceController {

    @GetMapping("/myBalance")
    public fun getBalanceDetails(): String  = "Balance from DB"
}
