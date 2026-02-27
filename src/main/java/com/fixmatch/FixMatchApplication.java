package com.fixmatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Application Class for FixMatch Gig Management System
 * 
 * @SpringBootApplication annotation enables:
 * - @Configuration: Tags the class as a source of bean definitions
 * - @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings
 * - @ComponentScan: Tells Spring to look for components, configurations, and services in the com.fixmatch package
 */
@SpringBootApplication
public class FixMatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(FixMatchApplication.class, args);
        System.out.println("\n==============================================");
        System.out.println("✅ FixMatch Backend Started Successfully!");
        System.out.println("🚀 Server running on: http://localhost:8080");
        System.out.println("📚 API Documentation: http://localhost:8080/api");
        System.out.println("==============================================\n");
    }
}
