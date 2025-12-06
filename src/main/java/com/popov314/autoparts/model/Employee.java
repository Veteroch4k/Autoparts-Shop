package com.popov314.autoparts.model;

import com.popov314.autoparts.model.reference_tables.City;
import com.popov314.autoparts.model.reference_tables.Street;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.yaml.snakeyaml.events.Event.ID;

@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "first_name", nullable = false, length = 25)
  private String firstName;

  @Column(name = "second_name", nullable = false, length = 25)
  private String secondName;

  @Column(name = "middle_name", nullable = false, length = 25)
  private String middleName;

  @Enumerated(EnumType.STRING)
  private GenderType gender;

  @Column(name = "birth_date", nullable = false)
  private LocalDate birthDate;

  @Column(name = "experience_years", nullable = false)
  @Range(min = 0, max = 80)
  private short experienceYears;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "city_id")
  private City city;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "city_id")
  private Street street;

  @Column(name = "house_number", nullable = false, length = 10)
  private String houseNumber;


}

enum GenderType {
  MAN,
  WOMAN,
}
