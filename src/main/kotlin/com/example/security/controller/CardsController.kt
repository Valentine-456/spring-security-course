package com.example.security.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CardsController {

    @GetMapping("/myCards")
    public fun getCardDetails(): String  = "Cards from DB"
}
