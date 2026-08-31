package com.rishiraj.productservice.service;

import com.rishiraj.productservice.dto.FakeStoreProductDto;
import com.rishiraj.productservice.model.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId);
    List<FakeStoreProductDto> getAllProducts();
    Product createProduct(Product product);
    void deleteProduct(Long productId);
    FakeStoreProductDto updateProduct(Long productId, FakeStoreProductDto product);

}
