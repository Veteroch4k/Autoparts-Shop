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
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  private Product product;

  @Column(name = "quantity", nullable = false)
  @Positive
  private short quantity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id")
  private Client client;

  @Column(name = "order_date", nullable = false)
  @CreationTimestamp
  private LocalDateTime orderDate;

  @Column(name = "order_price", nullable = false, precision = 10, scale = 2)
  @Positive
  private BigDecimal orderPrice;

}
