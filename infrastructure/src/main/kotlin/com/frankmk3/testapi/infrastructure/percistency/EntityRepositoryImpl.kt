package com.frankmk3.testapi.infrastructure.percistency

import com.frankmk3.testapi.application.port.EntityRepository
import com.frankmk3.testapi.domain.model.Entity
import com.frankmk3.testapi.infrastructure.percistency.entiry.ExampleEntity
import kotlin.jvm.optionals.getOrNull
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component

@Component
class EntityRepositoryImpl(private val repository: ExampleEntityRepositoryImpl) : EntityRepository {
    override fun findById(id: String): Entity? {
        return repository.findById(id).getOrNull()?.toDomain()
    }

    override fun save(entity: Entity): Entity {
        return repository.save(ExampleEntity.fromDomain(entity)).toDomain()
    }
}

interface ExampleEntityRepositoryImpl : CrudRepository<ExampleEntity, String>
