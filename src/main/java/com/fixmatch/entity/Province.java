package com.fixmatch.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Province Entity - Represents provinces in Rwanda
 * 
 * Relationships:
 * - One-to-Many with District (One province has many districts)
 * 
 * This entity demonstrates:
 * 1. Basic entity mapping
 * 2. One-to-Many relationship
 * 3. Unique constraints
 */
@Entity
@Table(name = "provinces")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String code; // e.g., "KGL", "EST", "WST"

    @Column(nullable = false, unique = true, length = 100)
    private String name; // e.g., "Kigali", "Eastern Province"

    /**
     * One-to-Many Relationship with District
     * - One province can have many districts
     * - mappedBy: Indicates that District entity owns the relationship
     * - cascade: Operations on Province will cascade to Districts
     * - orphanRemoval: If a district is removed from the list, it will be deleted
     */
    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<District> districts;

    // Constructor without relationships (useful for creation)
    public Province(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
