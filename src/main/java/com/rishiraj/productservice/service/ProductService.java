package com.rishiraj.productservice.service;

import com.rishiraj.productservice.dto.FakeStoreProductDto;
import com.rishiraj.productservice.exception.CategoryNotFound;
import com.rishiraj.productservice.exception.ProductNotFoundException;
import com.rishiraj.productservice.model.Product;
import com.rishiraj.productservice.projections.ProductProjection;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId)throws ProductNotFoundException;
    List<ProductProjection> getAllProducts();
    Product createProduct(Product product);
    void deleteProduct(Long productId);
    FakeStoreProductDto updateProduct(Long productId, FakeStoreProductDto product);
    List<ProductProjection> getProductByCategory(String category)throws CategoryNotFound;

}
