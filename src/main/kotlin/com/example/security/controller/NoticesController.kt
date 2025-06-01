package com.example.security.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class NoticesController {

    @GetMapping("/notices")
    public fun getNoticesDetails(): String  = "Notices from DB"
}
