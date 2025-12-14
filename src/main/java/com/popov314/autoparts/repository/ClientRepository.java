package com.popov314.autoparts.repository;

import com.popov314.autoparts.model.Client;
import com.popov314.autoparts.model.reference_tables.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {


  void deleteById(int id);

}
