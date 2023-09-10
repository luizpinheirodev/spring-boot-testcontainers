package dev.backend.luiz.springboottestcontainers.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Course(
    @Id
    val id: String? = null,
    val name: String? = null,
    val duration: String? = null,
    val price: Double? = 0.0
)