package com.technomist.e_commerce.model.repository;

import com.technomist.e_commerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
