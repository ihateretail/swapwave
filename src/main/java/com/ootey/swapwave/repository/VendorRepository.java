package com.ootey.swapwave.repository;

import com.ootey.swapwave.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Vendor entity.
 */
public interface VendorRepository extends JpaRepository<Vendor,Long>{

}
