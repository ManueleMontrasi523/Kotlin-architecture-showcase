package it.marketplace

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling

/**
 * Main entry point for the Marketplace Microservices Spring Boot application.
 * Configures entity scanning, JPA repositories, and enables scheduling.
 */
@SpringBootApplication(scanBasePackages = "it.marketplace")
@EntityScan(basePackages = "it.marketplace.entity")
@EnableJpaRepositories(basePackages = "it.marketplace.repository")
@EnableScheduling
open class StartApplication

fun main(args: Array<String>) {
    runApplication<StartApplication>(*args)
}
