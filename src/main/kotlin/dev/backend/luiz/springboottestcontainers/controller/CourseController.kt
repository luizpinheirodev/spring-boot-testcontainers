package dev.backend.luiz.springboottestcontainers.controller

import dev.backend.luiz.springboottestcontainers.domain.Course
import dev.backend.luiz.springboottestcontainers.service.CourseService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/courses")
class CourseController(private val courseService: CourseService) {

    @PostMapping
    fun addCourse(@RequestBody course: Course?): Mono<Course> {
        LOGGER.info("CourseController::addCourse method executed")
        return courseService.addNewCourse(course!!)
    }

    @GetMapping
    fun viewAllCourses(): Flux<Course> {
        LOGGER.info("CourseController::viewAllCourses method executed")
        return courseService.getAllAvailableCourses()
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(this::class.java)
    }
}