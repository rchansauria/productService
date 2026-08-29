package com.rishiraj.productservice.service;

import com.rishiraj.productservice.model.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(String productId);
    List<Product> getAllProducts();
    Product CreateProduct(Product product);

}
