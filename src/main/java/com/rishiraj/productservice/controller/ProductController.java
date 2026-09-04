package com.rishiraj.productservice.controller;

import com.rishiraj.productservice.dto.ErrorDto;
import com.rishiraj.productservice.dto.FakeStoreProductDto;
import com.rishiraj.productservice.exception.CategoryNotFound;
import com.rishiraj.productservice.exception.ProductNotFoundException;
import com.rishiraj.productservice.model.Product;
import com.rishiraj.productservice.projections.ProductProjection;
import com.rishiraj.productservice.service.ProductService;
import com.rishiraj.productservice.service.SelfProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;
    public ProductController(@Qualifier("SelfProductService") ProductService productService) {
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
    @ExceptionHandler(CategoryNotFound.class)
    public ResponseEntity<ErrorDto> handleCategoryNotFoundException(CategoryNotFound e) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(e.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductProjection>> getProducts() {
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

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductProjection>> getCategoryProducts(@PathVariable String category)throws CategoryNotFound {
        return  ResponseEntity.ok(productService.getProductByCategory(category));
    }


}
