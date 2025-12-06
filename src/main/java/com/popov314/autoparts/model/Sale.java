package com.popov314.autoparts.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
  private int id;

  @Column(name = "quantity", nullable = false)
  @Positive
  private short quantity;

  @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
  @Positive
  private BigDecimal totalPrice;

  @Column(name = "sale_date", nullable = false)
  @CreationTimestamp
  private LocalDateTime saleDate;


}
