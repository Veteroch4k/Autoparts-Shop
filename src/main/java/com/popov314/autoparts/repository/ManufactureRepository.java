package com.popov314.autoparts.repository;

import com.popov314.autoparts.model.Manufacture;
import com.popov314.autoparts.model.reference_tables.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufactureRepository extends JpaRepository<Manufacture, Integer> {

}
