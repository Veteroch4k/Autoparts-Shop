package com.popov314.autoparts.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "defective_products")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DefectiveProduct {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  private Product product;

  @Column(name = "product_quantity", nullable = false)
  @Positive
  private int productQuantity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "supplier_id")
  private Supplier supplier;

  @Column(name = "refund_amount", nullable = false ,precision = 10, scale = 2)
  private BigDecimal refundAmount;

  @Column(name = "return_date")
  @CreationTimestamp
  private LocalDateTime returnDate;

}
