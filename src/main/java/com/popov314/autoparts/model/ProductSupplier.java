package com.popov314.autoparts.model;

import io.micrometer.core.instrument.binder.netty4.NettyAllocatorMetrics;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import javax.print.attribute.standard.MediaSize.NA;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
class ProductSupplierId implements Serializable {
  private int productId;
  private int supplierId;

}

@Entity
@Table(name = "product_suppliers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductSupplier {

  @EmbeddedId
  private ProductSupplierId id;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("productId")
  @JoinColumn(name = "product_id")
  private Product product;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("supplierId")
  @JoinColumn(name = "supplier_id")
  private Supplier supplier;

  @Column(name = "delivery_days")
  @NotNull
  @Range(min = 1, max = 99)
  private short deliveryDays;


}
