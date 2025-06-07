package it.marketplace.microservices

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling

/**
 * Main entry point for the Marketplace Microservices Spring Boot application.
 * Configures entity scanning, JPA repositories, and enables scheduling.
 */
@SpringBootApplication(scanBasePackages = ["it.marketplace.microservices"])
@EntityScan(basePackages = ["it.marketplace.microservices.database.entity"])
@EnableJpaRepositories(basePackages = ["it.marketplace.microservices.database.repository"])
@EnableScheduling
open class StartApplication

fun main(args: Array<String>) {
    runApplication<StartApplication>(*args)
}
