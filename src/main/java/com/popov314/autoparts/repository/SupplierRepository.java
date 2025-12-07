package com.popov314.autoparts.repository;

import com.popov314.autoparts.model.Supplier;
import com.popov314.autoparts.model.reference_tables.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

}
