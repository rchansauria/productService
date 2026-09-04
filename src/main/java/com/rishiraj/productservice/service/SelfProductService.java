package com.rishiraj.productservice.service;

import com.rishiraj.productservice.dto.FakeStoreProductDto;
import com.rishiraj.productservice.exception.CategoryNotFound;
import com.rishiraj.productservice.exception.ProductNotFoundException;
import com.rishiraj.productservice.model.Category;
import com.rishiraj.productservice.model.Product;
import com.rishiraj.productservice.projections.ProductProjection;
import com.rishiraj.productservice.repository.CategoryRepo;
import com.rishiraj.productservice.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Product> product = productRepo.findById(productId);
        if (product.isPresent()) {
            return product.get();
        }
        throw  new ProductNotFoundException("Product not found");
    }

    @Override
    public List<ProductProjection> getAllProducts() {
        return productRepo.findAllProducts();
    }

//    @Override
//    public List<FakeStoreProductDto> getAllProducts() {
//        return productRepo.findAll();
//    }

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

    @Override
    public List<ProductProjection> getProductByCategory(String category) throws CategoryNotFound {
        Category cat = categoryRepo.findByTitle(category);
        if(cat==null){
            throw new CategoryNotFound("Category not found!");
        }
        else{
//            return productRepo.findByCategoryId(cat.getId());
//            return productRepo.findByCategoryIdNative(cat.getId());
            return productRepo.getProductByCategoryIdProjection(cat.getId());
        }

    }
}
