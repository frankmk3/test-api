package com.frankmk3.testapi.infrastructure.controller

import org.springframework.web.bind.annotation.*

@RestController
class HealthController {

    @GetMapping("/_health")
    fun health(): String {
        return "healthy"
    }
}
