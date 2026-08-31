package com.rishiraj.productservice.service;

import com.rishiraj.productservice.dto.FakeStoreProductDto;
import com.rishiraj.productservice.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getSingleProduct(Long productId) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId, FakeStoreProductDto.class
        );
        return fakeStoreProductDto.toProduct();

    }

    @Override
    public List<Product> getAllProducts() {
        System.out.println("getAllProducts");
        return List.of();
    }

    @Override
    public Product CreateProduct(Product product) {
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
