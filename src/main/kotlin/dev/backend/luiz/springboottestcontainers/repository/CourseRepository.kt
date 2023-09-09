package dev.backend.luiz.springboottestcontainers.repository

import dev.backend.luiz.springboottestcontainers.domain.Course
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface CourseRepository : ReactiveCrudRepository<Course, Int>