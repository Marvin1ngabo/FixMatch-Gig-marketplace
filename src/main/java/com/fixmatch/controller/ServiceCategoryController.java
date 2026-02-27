package com.fixmatch.controller;

import com.fixmatch.entity.ServiceCategory;
import com.fixmatch.service.ServiceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * ServiceCategoryController - REST API endpoints for ServiceCategory entity
 * 
 * Base URL: /api/categories
 */
@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class ServiceCategoryController {

    @Autowired
    private ServiceCategoryService categoryService;

    /**
     * Create new category
     * 
     * POST /api/categories
     * Body: { "name": "Plumbing", "icon": "Wrench", "description": "..." }
     */
    @PostMapping
    public ResponseEntity<ServiceCategory> createCategory(@RequestBody ServiceCategory category) {
        try {
            ServiceCategory created = categoryService.createCategory(category);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * REQUIREMENT #3: Get all categories with Sorting
     * 
     * GET /api/categories?sortBy=name&direction=asc
     */
    @GetMapping
    public ResponseEntity<List<ServiceCategory>> getAllCategories(
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        
        Sort sort = direction.equalsIgnoreCase("asc") 
            ? Sort.by(sortBy).ascending() 
            : Sort.by(sortBy).descending();
        
        List<ServiceCategory> categories = categoryService.getAllCategories(sort);
        return ResponseEntity.ok(categories);
    }

    /**
     * Get category by ID
     * 
     * GET /api/categories/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ServiceCategory> getCategoryById(@PathVariable Long id) {
        try {
            ServiceCategory category = categoryService.getCategoryById(id);
            return ResponseEntity.ok(category);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Get category by name
     * 
     * GET /api/categories/name/{name}
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<ServiceCategory> getCategoryByName(@PathVariable String name) {
        try {
            ServiceCategory category = categoryService.getCategoryByName(name);
            return ResponseEntity.ok(category);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
