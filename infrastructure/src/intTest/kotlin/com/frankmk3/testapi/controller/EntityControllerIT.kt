package com.frankmk3.testapi.controller

import com.frankmk3.testapi.BaseIntegrationTest
import com.frankmk3.testapi.application.port.EntityRepository
import com.frankmk3.testapi.domain.model.Entity
import io.restassured.RestAssured.`when`
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.core.Is
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class EntityControllerIT : BaseIntegrationTest() {

    @Autowired lateinit var repository: EntityRepository

    @Test
    fun `getEntity - when the entity does not exist, should return 404`() {
        `when`().get(getURL("non-existing-id")).then().statusCode(Is.`is`(404))
    }

    @Test
    fun `getEntity - when the entity exist should return 200`() {
        val entity = Entity("1", "entity")
        repository.save(entity)
        val response = `when`().get(getURL("1")).then().statusCode(Is.`is`(200)).extract().response()
        val responseEntity = response.`as`(Entity::class.java)

        assertThat(responseEntity).isEqualTo(entity)
    }

    private fun baseURL(): String {
        return "http://localhost:$port/entities"
    }

    private fun getURL(id: String): String {
        return "${baseURL()}/$id"
    }
}
