package com.service.jpqldemo.module.v1.product.repository;

import com.service.jpqldemo.module.v1.product.dao.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT a FROM Product a")
    List<Product> findAllProductJpql();

    @Query(nativeQuery = true, value = "SELECT * FROM products")
    List<Product> findAllProductNativeQuery();

    @Query(nativeQuery = true, value = "SELECT * FROM products " +
            "LEFT JOIN product_category ON products.product_category_id=product_category.product_cat_id")
    List<Product> findAllProductWithJoins();

    @Query(nativeQuery = true, value = "SELECT * FROM products " +
            "LEFT JOIN product_category ON products.product_category_id=product_category.product_cat_id " +
            "WHERE products.product_id = :id")
    Optional<Product> findProductByIdNative(@Param("id") Integer id);
}
