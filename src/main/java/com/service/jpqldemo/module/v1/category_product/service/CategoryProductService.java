package com.service.jpqldemo.module.v1.category_product.service;

import com.service.jpqldemo.module.v1.category_product.dao.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface CategoryProductService {
    List<ProductCategory> findAllCategoryProduct();
    Long countAllCategoryProduct();
    Optional<ProductCategory> findCategoryProductById(Integer categoryProductId);
    ProductCategory createNewProductCategory(ProductCategory productCategoryPayload);
    ProductCategory updateCategoryProduct(Integer categoryProductId, ProductCategory productCategoryPayload);
    ProductCategory deleteCategoryProduct(Integer categoryProductId);

}
