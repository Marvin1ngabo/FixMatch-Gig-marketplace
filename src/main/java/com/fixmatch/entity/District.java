package com.fixmatch.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * District Entity - Represents districts within provinces
 * 
 * Relationships:
 * - Many-to-One with Province (Many districts belong to one province)
 * 
 * This entity demonstrates:
 * 1. Many-to-One relationship (the "Many" side of One-to-Many)
 * 2. Foreign key usage
 * 3. JsonIgnore to prevent circular references
 */
@Entity
@Table(name = "districts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name; // e.g., "Gasabo", "Kicukiro", "Nyarugenge"

    /**
     * Many-to-One Relationship with Province
     * - Many districts belong to one province
     * - @JoinColumn: Specifies the foreign key column name
     * - nullable = false: Every district must belong to a province
     * - @JsonIgnore: Prevents infinite recursion when serializing to JSON
     */
    @ManyToOne
    @JoinColumn(name = "province_id", nullable = false)
    @JsonIgnore
    private Province province;

    // Constructor without relationships
    public District(String name) {
        this.name = name;
    }
}
