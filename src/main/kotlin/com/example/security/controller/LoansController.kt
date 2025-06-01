package com.example.security.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LoansController {

    @GetMapping("/myLoans")
    public fun getLoansDetails(): String  = "Loans from DB"
}
