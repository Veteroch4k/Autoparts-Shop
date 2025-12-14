package com.popov314.autoparts.repository;

import com.popov314.autoparts.model.ProductSupplier;
import com.popov314.autoparts.model.ProductSupplierId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSupplierRepository extends JpaRepository<ProductSupplier, ProductSupplierId> {

}
