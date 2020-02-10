package com.service.jpqldemo.module.v1.category_product.dao;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product_category")
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_cat_id")
    private Integer productCategoryId;

    @Column(name = "product_category_name")
    private String productCategoryName;
}
