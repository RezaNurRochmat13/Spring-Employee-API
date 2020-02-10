package com.service.jpqldemo.module.v1.product.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UpdateProductDto {
    private String productName;
    private String productSeller;
    private String productDescription;
    private Integer categoryProduct;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSeller() {
        return productSeller;
    }

    public void setProductSeller(String productSeller) {
        this.productSeller = productSeller;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Integer getCategoryProduct() {
        return categoryProduct;
    }

    public void setCategoryProduct(Integer categoryProduct) {
        this.categoryProduct = categoryProduct;
    }
}
