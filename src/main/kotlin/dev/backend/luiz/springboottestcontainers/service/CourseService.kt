package dev.backend.luiz.springboottestcontainers.service

import dev.backend.luiz.springboottestcontainers.domain.Course
import dev.backend.luiz.springboottestcontainers.repository.CourseRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Service
class CourseService(private val courseRepository: CourseRepository) {

    fun addNewCourse(course: Course): Mono<Course> {
        LOGGER.info("CourseService::addNewCourse method executed")
        return courseRepository.save(course)
    }

    fun getAllAvailableCourses(): Flux<Course> {
        LOGGER.info("CourseService::getAllAvailableCourses method executed")
        return courseRepository.findAll()
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(this::class.java)
    }
}