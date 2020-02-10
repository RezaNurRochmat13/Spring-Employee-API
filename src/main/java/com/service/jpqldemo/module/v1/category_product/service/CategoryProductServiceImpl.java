package com.service.jpqldemo.module.v1.category_product.service;

import com.service.jpqldemo.exception.ResourceNotFound;
import com.service.jpqldemo.module.v1.category_product.dao.ProductCategory;
import com.service.jpqldemo.module.v1.category_product.repository.CategoryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryProductServiceImpl implements CategoryProductService {
    @Autowired
    CategoryProductRepository categoryProductRepository;

    @Override
    public List<ProductCategory> findAllCategoryProduct() {
        return categoryProductRepository.findAll();
    }

    @Override
    public Long countAllCategoryProduct() {
        return categoryProductRepository.count();
    }

    @Override
    public Optional<ProductCategory> findCategoryProductById(Integer categoryProductId) {
        Optional<ProductCategory> productCategory = Optional
                .ofNullable(categoryProductRepository.findById(categoryProductId)
                .orElseThrow(() -> new ResourceNotFound("Category product not found with id : " + categoryProductId)));

        return productCategory;
    }

    @Override
    public ProductCategory createNewProductCategory(ProductCategory productCategoryPayload) {
        return categoryProductRepository.save(productCategoryPayload);
    }

    @Override
    public ProductCategory updateCategoryProduct(Integer categoryProductId, ProductCategory productCategoryPayload) {
        ProductCategory productCategoryUpdate = categoryProductRepository.findById(categoryProductId)
                .orElseThrow(() -> new ResourceNotFound("Category product not found with id : " + categoryProductId));
        productCategoryUpdate.setProductCategoryName(productCategoryPayload.getProductCategoryName());
        categoryProductRepository.save(productCategoryUpdate);

        return productCategoryUpdate;
    }

    @Override
    public ProductCategory deleteCategoryProduct(Integer categoryProductId) {
        ProductCategory productCategoryDelete = categoryProductRepository.findById(categoryProductId)
                .orElseThrow(() -> new ResourceNotFound("Category product not found with id : " + categoryProductId));
        categoryProductRepository.delete(productCategoryDelete);

        return productCategoryDelete;
    }
}
