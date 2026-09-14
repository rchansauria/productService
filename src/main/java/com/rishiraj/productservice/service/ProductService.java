package com.rishiraj.productservice.service;

import com.rishiraj.productservice.ProductServiceApplication;
import com.rishiraj.productservice.dto.FakeStoreProductDto;
import com.rishiraj.productservice.exception.CategoryNotFound;
import com.rishiraj.productservice.exception.ProductNotFoundException;
import com.rishiraj.productservice.model.Product;
import com.rishiraj.productservice.projections.ProductProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId)throws ProductNotFoundException;
    Page<Product> getAllProducts(int pageSize, int pageNo, String sortBy, String dir)throws ProductNotFoundException;
    Product createProduct(Product product);
    void deleteProduct(Long productId);
//    Product updateProduct(Long productId, Product product)throws ProductNotFoundException;
    List<Product> getProductByCategory(String category)throws CategoryNotFound;

}
