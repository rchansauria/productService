package com.rishiraj.productservice.controller;

import com.rishiraj.productservice.dto.ErrorDto;
import com.rishiraj.productservice.dto.FakeStoreProductDto;
import com.rishiraj.productservice.exception.ProductNotFoundException;
import com.rishiraj.productservice.model.Product;
import com.rishiraj.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);

    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) throws ProductNotFoundException {
        Product currentProduct = productService.getSingleProduct(id);

        return new ResponseEntity<>(productService.getSingleProduct(id), HttpStatus.OK);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException e) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(e.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/products")
    public ResponseEntity<List<FakeStoreProductDto>> getProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct( @PathVariable Long  id){
        productService.deleteProduct(id);
       return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public FakeStoreProductDto updateProduct(@PathVariable Long id, @RequestBody FakeStoreProductDto fakeStoreProductDto) {

    return productService.updateProduct(id, fakeStoreProductDto);
    }


}
