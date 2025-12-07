package com.popov314.autoparts.repository;

import com.popov314.autoparts.model.WorkRecord;
import com.popov314.autoparts.model.reference_tables.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRecordRepository extends JpaRepository<WorkRecord, Integer> {

}
