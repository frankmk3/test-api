package com.frankmk3.testapi.application.service

import com.frankmk3.testapi.application.port.EntityRepository
import com.frankmk3.testapi.domain.model.Entity

class EntityService(private val entityRepository: EntityRepository) {
    fun getEntity(id: String): Entity? {
        return entityRepository.findById(id)
    }

    fun createEntity(entity: Entity): Entity {
        return entityRepository.save(entity)
    }
}
