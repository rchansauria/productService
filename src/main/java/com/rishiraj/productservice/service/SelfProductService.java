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

import javax.swing.text.html.Option;
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
    public Product updateProduct(Long productId, Product product) throws ProductNotFoundException {
            Optional<Product> p = productRepo.findById(productId);
            if(!p.isPresent()){
                throw  new ProductNotFoundException("Product not found");
            }
            Product updatedProduct = p.get();
            if(product.getTitle()!=null){
                updatedProduct.setTitle(product.getTitle());
            }
            if(product.getDescription()!=null){
                updatedProduct.setDescription(product.getDescription());
            }
            if(product.getCategory()!=null){
                Category category =
                        categoryRepo.findByTitle(product.getCategory().getTitle());

                if (category == null) {
                    Category newCategory = new Category();
                    newCategory.setTitle(product.getCategory().getTitle());

                    category = categoryRepo.save(newCategory);
                }

                updatedProduct.setCategory(category);
            }
            if(product.getPrice()!=0.0){
                updatedProduct.setPrice(product.getPrice());
            }
            if(product.getImageUrl()!=null){
                updatedProduct.setImageUrl(product.getImageUrl());
            }
            return productRepo.save(updatedProduct);

    }



    @Override
    public List<Product> getProductByCategory(String category) throws CategoryNotFound {
        Category cat = categoryRepo.findByTitle(category);
        if(cat==null){
            throw new CategoryNotFound("Category not found!");
        }
        else{
            return productRepo.findByCategoryId(cat.getId());
//            return productRepo.findByCategoryIdNative(cat.getId());
//            return productRepo.getProductByCategoryIdProjection(cat.getId());
        }

    }
}
