package com.frankmk3.testapi.controller

import com.frankmk3.testapi.BaseIntegrationTest
import io.restassured.RestAssured.`when`
import org.hamcrest.Matchers
import org.hamcrest.core.Is
import org.junit.jupiter.api.Test

internal class HealthControllerIT : BaseIntegrationTest() {

    @Test
    fun `health - should return healthy`() {
        `when`()
            .get("http://localhost:$port/_health")
            .then()
            .statusCode(Is.`is`(200))
            .body(Matchers.containsString("healthy"))
    }
}
