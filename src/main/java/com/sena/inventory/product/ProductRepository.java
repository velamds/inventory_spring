package com.sena.inventory.product;

import com.sena.inventory.brand.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByStateTrue();

    List<Product> findByPriceGreaterThan(Double price);

    List<Product> findByBrand(Brand brand);
}
