package com.popov314.autoparts.model;

import com.popov314.autoparts.model.reference_tables.City;
import com.popov314.autoparts.model.reference_tables.Street;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "manufactures")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Manufacture {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name", nullable = false, length = 200, unique = true)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "city_id")
  private City city;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "street_id")
  private Street street;

  @Column(name = "house_number", nullable = false, length = 10)
  private String houseNumber;


}
