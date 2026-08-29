package com.rishiraj.productservice.service;

import com.rishiraj.productservice.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{
    @Override
    public Product getSingleProduct(String productId) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        System.out.println("getAllProducts");
        return List.of();
    }

    @Override
    public Product CreateProduct(Product product) {
        return null;
    }
}
