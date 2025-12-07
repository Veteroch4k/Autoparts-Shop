package com.popov314.autoparts.repository;

import com.popov314.autoparts.model.ProductSupplier;
import com.popov314.autoparts.model.reference_tables.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSupplierRepository extends JpaRepository<ProductSupplier, Integer> {

}
