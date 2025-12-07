package com.popov314.autoparts.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clients")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "first_name", nullable = false, length = 25)
  @NotBlank(message = "Имя обязательно для заполнения")
  @Size(max = 22, message = "Имя не может превышать длину в {max} символа")
  private String firstName;

  @Column(name = "second_name", nullable = false, length = 25)
  @NotBlank(message = "Фамилия обязательна для заполнения")
  @Size(max = 22, message = "Фамилия не может превышать длину в {max} символа")
  private String secondName;

  @Column(name = "phone_number", nullable = false,  length = 22)
  @NotBlank(message = "Номер телефона обязателен для заполнения")
  @Size(max = 22, message = "Номер телефона не может превышать длину в {max} символа")
  private String phoneNumber;

}
