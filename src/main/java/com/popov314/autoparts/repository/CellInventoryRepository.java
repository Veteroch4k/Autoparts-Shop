package com.popov314.autoparts.repository;

import com.popov314.autoparts.model.CellInventory;
import com.popov314.autoparts.model.reference_tables.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CellInventoryRepository extends JpaRepository<CellInventory, Integer> {

}
