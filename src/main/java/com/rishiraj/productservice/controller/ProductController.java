package com.rishiraj.productservice.controller;

import com.rishiraj.productservice.model.Product;
import com.rishiraj.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public void createProduct() {

    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable Long id) {
        Product currentProduct = productService.getSingleProduct(id);
        return currentProduct;
    }

    @GetMapping("/products")
    public void getProducts() {
        productService.getAllProducts();
    }


}
