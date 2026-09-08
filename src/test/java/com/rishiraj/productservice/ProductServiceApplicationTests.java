package com.rishiraj.productservice;

import com.rishiraj.productservice.model.Category;
import com.rishiraj.productservice.model.Product;
import com.rishiraj.productservice.repository.CategoryRepo;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceApplicationTests {

    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    void contextLoads() {
    }

//    @Test
//    @Transactional
//    void fetchCategoryLazy(){
//        Category category = categoryRepo.findById(1L).get();
//        System.out.println(category.getId());
//        System.out.println("We are done here");
//
//        List<Product> currentProducts = category.getProducts();
//        System.out.println(currentProducts.size());
//        // It is going to execute a new query to fetch list of products
//
//        System.out.println("Products fetched");
//    }

}
