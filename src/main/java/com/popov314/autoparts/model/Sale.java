package com.popov314.autoparts.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "sales")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sale {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "quantity", nullable = false)
  @Positive
  @NotNull
  private short quantity;

  @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
  @Positive
  @NotNull
  @Digits(integer = 8, fraction = 2)
  private BigDecimal totalPrice;

  @Column(name = "sale_date", nullable = false)
  @CreationTimestamp
  @NotNull
  private LocalDateTime saleDate;


}
