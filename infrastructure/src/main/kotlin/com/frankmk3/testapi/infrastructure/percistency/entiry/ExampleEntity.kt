package com.frankmk3.testapi.infrastructure.percistency.entiry

import com.frankmk3.testapi.domain.model.Entity
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("example")
data class ExampleEntity(@Id val id: String, val name: String) {
    fun toDomain(): Entity {
        return Entity(id = id, name = name)
    }

    companion object {
        fun fromDomain(entity: Entity): ExampleEntity {
            return ExampleEntity(id = entity.id, name = entity.name)
        }
    }
}
