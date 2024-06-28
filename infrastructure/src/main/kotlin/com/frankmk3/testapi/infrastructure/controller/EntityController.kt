package com.frankmk3.testapi.infrastructure.controller

import com.frankmk3.testapi.application.service.EntityService
import com.frankmk3.testapi.domain.model.Entity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/entities")
class EntityController(private val entityService: EntityService) {

    @GetMapping("/{id}")
    fun getEntity(@PathVariable id: String): ResponseEntity<Entity> {
        val entity = entityService.getEntity(id)
        return if (entity != null) {
            ResponseEntity.ok(entity)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createEntity(@RequestBody entity: Entity): ResponseEntity<Entity> {
        return ResponseEntity.ok(entityService.createEntity(entity))
    }
}
