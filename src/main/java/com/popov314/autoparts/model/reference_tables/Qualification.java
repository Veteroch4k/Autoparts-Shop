package com.popov314.autoparts.model.reference_tables;

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
@Table(name = "qualifications")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Qualification {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name", nullable = false, unique = true, length = 25)
  @NotBlank(message = "Название квалификации обязательно для заполнения")
  @Size(max = 25, message = "Название квалификации не может превышать длину в {max} символов")
  private String name;

}
