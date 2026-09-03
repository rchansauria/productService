package com.rishiraj.productservice.service;

import com.rishiraj.productservice.dto.FakeStoreProductDto;
import com.rishiraj.productservice.exception.ProductNotFoundException;
import com.rishiraj.productservice.model.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service("FakeProductStoreService")
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId, FakeStoreProductDto.class
        );
        if(fakeStoreProductDto == null){
            throw new ProductNotFoundException("Product not found with id " + productId);
        }
        return fakeStoreProductDto.toProduct();

    }

    @Override
    public List<FakeStoreProductDto> getAllProducts() {
        FakeStoreProductDto[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products", FakeStoreProductDto[].class
        );
        return Arrays.asList(response);
    }

    @Override
    public void deleteProduct(Long productId) {
        String url = "https://fakestoreapi.com/products/" + productId;
        restTemplate.delete(url);
    }

    @Override
    public FakeStoreProductDto updateProduct(Long productId, FakeStoreProductDto product) {
        String url = "https://fakestoreapi.com/products/" + productId;

        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.PUT,
                        new HttpEntity<>(product),
                        FakeStoreProductDto.class
                );

        return response.getBody();
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDto fs = new FakeStoreProductDto();
        fs.setId(product.getId());
        fs.setDescription(product.getDescription());
        fs.setPrice(product.getPrice());
        fs.setTitle(product.getTitle());
        fs.setCategory(product.getCategory().getTitle());
        fs.setImage(product.getImageUrl());


    FakeStoreProductDto response = restTemplate.postForObject(
            "https://fakestoreapi.com/products", fs, FakeStoreProductDto.class
    );
    return response.toProduct();
    }
}
