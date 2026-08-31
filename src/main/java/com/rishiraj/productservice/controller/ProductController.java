package com.rishiraj.productservice.controller;

import com.rishiraj.productservice.dto.FakeStoreProductDto;
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
    public List<FakeStoreProductDto> getProducts() {
        return productService.getAllProducts();
    }
    @DeleteMapping("/delete/{id}")
    public void deleteProduct( @PathVariable Long  id){
        productService.deleteProduct(id);
    }

    @PutMapping("/update/{id}")
    public FakeStoreProductDto updateProduct(@PathVariable Long id, @RequestBody FakeStoreProductDto fakeStoreProductDto) {

    return productService.updateProduct(id, fakeStoreProductDto);
    }


}
