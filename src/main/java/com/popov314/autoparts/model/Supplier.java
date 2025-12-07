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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "suppliers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Supplier {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name", nullable = false, unique = true, length = 200)
  @NotBlank(message = "Название поставщика обязательно для заполнения")
  @Size(max = 200, message = "Название поставщика не может превышать длину в {max} символов")
  private String name;

  @Column(name = "phone_number", nullable = false, length = 22)
  @NotBlank(message = "Номер телефона обязателен для заполнения")
  @Size(max = 22, message = "Номер телефона не может превышать длину в {max} символа")
  private String phoneNumber;

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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employee_id")
  private Employee employee;


}
