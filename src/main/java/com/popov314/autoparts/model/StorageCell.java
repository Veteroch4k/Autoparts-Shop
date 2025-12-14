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
@Table(name = "storage_cells")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StorageCell {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "cell_number", nullable = false, unique = true, length = 10)
  @NotBlank(message = "Номер ячейки обязателен для заполнения")
  @Size(max = 10, message = "Номер ячейки не может превышать длину в {max} символов")
  private String cellNumber;

}
