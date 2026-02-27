package com.fixmatch.service;

import com.fixmatch.entity.District;
import com.fixmatch.entity.Province;
import com.fixmatch.repository.DistrictRepository;
import com.fixmatch.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * LocationService - Business logic for Province and District entities
 * 
 * This service demonstrates:
 * 1. Saving Location (Requirement #2)
 * 2. Handling relationships between Province and District
 * 3. Transaction management
 */
@Service
@Transactional
public class LocationService {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private DistrictRepository districtRepository;

    /**
     * REQUIREMENT #2: Save Province
     * 
     * Logic:
     * - Check if province already exists using existsByCode()
     * - If not, save the province
     * - Return the saved province with generated ID
     */
    public Province saveProvince(Province province) {
        // Check if province already exists
        if (provinceRepository.existsByCode(province.getCode())) {
            throw new RuntimeException("Province with code " + province.getCode() + " already exists");
        }
        
        // Save and return
        return provinceRepository.save(province);
    }

    /**
     * REQUIREMENT #2: Save District
     * 
     * Logic:
     * - Validate that province exists
     * - Set the province relationship
     * - Save the district (foreign key province_id is automatically set)
     */
    public District saveDistrict(District district, Long provinceId) {
        // Find province
        Province province = provinceRepository.findById(provinceId)
            .orElseThrow(() -> new RuntimeException("Province not found with id: " + provinceId));
        
        // Set relationship
        district.setProvince(province);
        
        // Save district (JPA automatically handles the foreign key)
        return districtRepository.save(district);
    }

    /**
     * Get all provinces
     */
    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    /**
     * Get province by code
     */
    public Province getProvinceByCode(String code) {
        return provinceRepository.findByCode(code)
            .orElseThrow(() -> new RuntimeException("Province not found with code: " + code));
    }

    /**
     * Get all districts by province with Pagination
     * 
     * Demonstrates: Pagination implementation
     */
    public Page<District> getDistrictsByProvince(Long provinceId, Pageable pageable) {
        return districtRepository.findByProvinceId(provinceId, pageable);
    }

    /**
     * Get all districts by province
     */
    public List<District> getDistrictsByProvince(Long provinceId) {
        return districtRepository.findByProvinceId(provinceId);
    }

    /**
     * Check if province exists by code (Requirement #7)
     */
    public boolean provinceExists(String code) {
        return provinceRepository.existsByCode(code);
    }
}
