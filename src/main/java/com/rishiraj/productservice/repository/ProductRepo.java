package com.rishiraj.productservice.repository;

import com.rishiraj.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
            Product findByTitle(String Title);
}
