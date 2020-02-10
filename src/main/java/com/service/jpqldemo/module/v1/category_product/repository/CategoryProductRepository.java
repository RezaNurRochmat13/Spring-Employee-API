package com.service.jpqldemo.module.v1.category_product.repository;

import com.service.jpqldemo.module.v1.category_product.dao.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryProductRepository extends JpaRepository<ProductCategory, Integer> { }
