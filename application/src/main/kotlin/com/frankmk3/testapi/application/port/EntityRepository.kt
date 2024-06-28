package com.frankmk3.testapi.application.port

import com.frankmk3.testapi.domain.model.Entity

interface EntityRepository {
    fun findById(id: String): Entity?

    fun save(entity: Entity): Entity
}
