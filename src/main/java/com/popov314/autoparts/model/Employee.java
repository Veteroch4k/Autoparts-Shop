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
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
  private Integer id;

  @Column(name = "first_name", nullable = false, length = 25)
  @NotBlank(message = "Имя обязательно для заполнения")
  @Size(max = 25, message = "Имя не может превышать длину в {max} символов")
  private String firstName;

  @Column(name = "second_name", nullable = false, length = 25)
  @NotBlank(message = "Фамилия обязательна для заполнения")
  @Size(max = 25, message = "Фамилия не может превышать длину в {max} символов")
  private String secondName;

  @Column(name = "middle_name", nullable = false, length = 25)
  @NotBlank(message = "Отчество обязательно для заполнения")
  @Size(max = 25, message = "Отчество не может превышать длину в {max} символов")
  private String middleName;

  @Enumerated(EnumType.STRING)
  private GenderType gender;

  @Column(name = "birth_date", nullable = false)
  private LocalDate birthDate;

  @Column(name = "experience_years", nullable = false)
  @Range(min = 0, max = 80)
  @NotNull
  private short experienceYears;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "city_id")
  private City city;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "street_id")
  private Street street;

  @Column(name = "house_number", nullable = false, length = 10)
  @NotBlank(message = "Номер дома обязателен для заполнения")
  @Size(max = 10, message = "Номер дома не может превышать длину в {max} символов")
  private String houseNumber;


}

enum GenderType {
  MAN,
  WOMAN,
}
