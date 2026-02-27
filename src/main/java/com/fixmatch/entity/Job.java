package com.fixmatch.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Job Entity - Represents job postings/bookings
 * 
 * Relationships:
 * - Many-to-One with User (client who posted the job)
 * - Many-to-One with User (provider who accepted the job)
 * - Many-to-One with ServiceCategory
 * - Many-to-One with District (job location)
 * 
 * This entity demonstrates:
 * 1. Multiple Many-to-One relationships
 * 2. Self-referencing relationships (User as both client and provider)
 * 3. Enum usage for status
 */
@Entity
@Table(name = "jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(precision = 10, scale = 2)
    private BigDecimal budget;

    /**
     * JobStatus Enum - Tracks the status of the job
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobStatus status = JobStatus.OPEN;

    /**
     * Many-to-One with User (Client)
     * - The user who posted the job
     */
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    /**
     * Many-to-One with User (Provider)
     * - The provider who accepted/is working on the job
     * - Can be null if job is not yet accepted
     */
    @ManyToOne
    @JoinColumn(name = "provider_id")
    private User provider;

    /**
     * Many-to-One with ServiceCategory
     */
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ServiceCategory category;

    /**
     * Many-to-One with District (Job Location)
     */
    @ManyToOne
    @JoinColumn(name = "district_id", nullable = false)
    private District district;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
