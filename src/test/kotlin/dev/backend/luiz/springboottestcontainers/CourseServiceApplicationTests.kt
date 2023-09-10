package dev.backend.luiz.springboottestcontainers

import dev.backend.luiz.springboottestcontainers.controller.CourseController
import dev.backend.luiz.springboottestcontainers.domain.Course
import org.junit.Before
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper
import java.text.ParseException


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@AutoConfigureMockMvc
class CourseServiceApplicationTests {

    @Autowired
    private var mockMvc: MockMvc? = null

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Before
    fun setup() {
        mockMvc = MockMvcBuilders
            .standaloneSetup(CourseController::class.java)
            .build()
    }

    companion object {
        @JvmStatic
        @Container
        var mongoDBContainer = MongoDBContainer("mongo:4.4.2")

        @JvmStatic
        @DynamicPropertySource
        fun setProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongodb.uri") { mongoDBContainer.replicaSetUrl }
        }

        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            mongoDBContainer.start()
        }

        @JvmStatic
        @AfterAll
        fun afterAll() {
            mongoDBContainer.stop()
        }
    }

    @Test
    fun addNewCourseTest() {
        val course = Course(
            name = "test-course",
            price = 100.0,
            duration = "0 month"
        )

        webTestClient
            .post()
            .uri("/courses")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(course)
            .exchange()
            .expectStatus().isEqualTo(HttpStatus.OK)
            .expectBody()
            .jsonPath("$.id").isNotEmpty
    }

    @Test
    fun getAllTheCoursesTest() {
        webTestClient
            .get()
            .uri("/courses")
            .exchange()
            .expectBody()
            .consumeWith(System.out::println)
            .jsonPath("$[0].id").isNotEmpty
    }

    private fun asJsonString(`object`: Any): String {
        try {
            return ObjectMapper().writeValueAsString(`object`)
        } catch (e: JsonProcessingException) {
            e.printStackTrace()
            throw ParseException(e.toString(), 1)
        }
    }


}