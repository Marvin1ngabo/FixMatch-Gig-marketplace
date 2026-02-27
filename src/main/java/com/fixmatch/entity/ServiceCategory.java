package com.fixmatch.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * ServiceCategory Entity - Represents categories of services
 * 
 * Relationships:
 * - One-to-Many with Job (One category has many jobs)
 * 
 * Examples: Plumbing, Cleaning, Electrical, Painting, Moving, Handyman
 */
@Entity
@Table(name = "service_categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(length = 50)
    private String icon; // Icon name for frontend (e.g., "Wrench", "Sparkles")

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "provider_count")
    private Integer providerCount = 0;

    /**
     * One-to-Many Relationship with Job
     * - One category can have many jobs
     */
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Job> jobs;

    // Constructor without relationships
    public ServiceCategory(String name, String icon, String description) {
        this.name = name;
        this.icon = icon;
        this.description = description;
    }
}
