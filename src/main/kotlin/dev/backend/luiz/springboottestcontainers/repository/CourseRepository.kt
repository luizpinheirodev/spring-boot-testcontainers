package dev.backend.luiz.springboottestcontainers.repository

import dev.backend.luiz.springboottestcontainers.domain.Course
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface CourseRepository : ReactiveMongoRepository<Course, String>