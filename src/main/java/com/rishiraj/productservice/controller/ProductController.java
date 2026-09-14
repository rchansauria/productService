package com.rishiraj.productservice.controller;

import com.rishiraj.productservice.client.PaymentClientService;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    private ProductService productService;
    private PaymentClientService paymentClientService;

    public ProductController(@Qualifier("FakeProductStoreService") ProductService productService, PaymentClientService paymentClientService) {
        this.productService = productService;
        this.paymentClientService = paymentClientService;
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


    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "1") int pageSize,
                                                                  @RequestParam(defaultValue = "price") String sortBy,  @RequestParam(defaultValue = "asc") String direction)throws ProductNotFoundException {
        return new ResponseEntity<>(productService.getAllProducts(pageNo,pageSize,sortBy, direction), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct( @PathVariable Long  id){
        productService.deleteProduct(id);
       return ResponseEntity.noContent().build();
    }

//    @PutMapping("/update/{id}")
//    public Product updateProduct(@PathVariable Long id, @RequestBody Product product)throws ProductNotFoundException {
//
//    return productService.updateProduct(id, product);
//    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getCategoryProducts(@PathVariable String category)throws CategoryNotFound {
        return  ResponseEntity.ok(productService.getProductByCategory(category));
    }

    @PostMapping("/orders/buy")
    public ResponseEntity<String> buyOrder() {

        String paymentUrl = paymentClientService.createPayment(
                "ORDER123",
                100000L
        );

        return ResponseEntity.ok(paymentUrl);
    }


}
