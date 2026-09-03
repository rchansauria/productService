package com.rishiraj.productservice.service;

import com.rishiraj.productservice.dto.FakeStoreProductDto;
import com.rishiraj.productservice.exception.ProductNotFoundException;
import com.rishiraj.productservice.model.Category;
import com.rishiraj.productservice.model.Product;
import com.rishiraj.productservice.repository.CategoryRepo;
import com.rishiraj.productservice.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("SelfProductService")
public class SelfProductService implements ProductService{
    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;

    public SelfProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<FakeStoreProductDto> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        Category cat = categoryRepo.findByTitle(product.getCategory().getTitle());
        if(cat==null){
            Category newCat = new Category();
            newCat.setTitle(product.getCategory().getTitle());
            Category newRow = categoryRepo.save(newCat);
            product.setCategory(newRow);
        }
        else{
            product.setCategory(cat);
        }

        Product savedProduct = productRepo.save(product);
        return savedProduct;
    }

    @Override
    public void deleteProduct(Long productId) {

    }

    @Override
    public FakeStoreProductDto updateProduct(Long productId, FakeStoreProductDto product) {
        return null;
    }
}
