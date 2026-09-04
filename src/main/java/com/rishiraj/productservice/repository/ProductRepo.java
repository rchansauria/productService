package com.rishiraj.productservice.repository;

import com.rishiraj.productservice.model.Category;
import com.rishiraj.productservice.model.Product;
import com.rishiraj.productservice.projections.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
            Product findByTitle(String Title);


            @Query(value = "SELECT id, title, price FROM product", nativeQuery = true)
            List<ProductProjection> findAllProducts();

           //Implement HQL:
            @Query("select  p from Product p where p.category.id= :categoryId")
            List<Product> findByCategoryId(@Param("categoryId") Long category);

            //Implement same Method by native query:
            @Query(value = "select * from product p where p.category_id= :categoryId", nativeQuery = true)
            List<Product> findByCategoryIdNative(@Param("categoryId") Long categoryId);

            //Implement HQL with Projection:
            @Query("select p.title as title, p.id as id from Product p where p.category.id = :categoryId")
            List<ProductProjection> getProductByCategoryIdProjection(@Param("categoryId") Long categoryId);
}
