package com.service.jpqldemo.module.v1.product.service;

import com.service.jpqldemo.module.v1.product.dao.Product;
import com.service.jpqldemo.module.v1.product.dto.CreateProductDto;
import com.service.jpqldemo.module.v1.product.dto.DetailProductDto;
import com.service.jpqldemo.module.v1.product.dto.ListProductDto;
import com.service.jpqldemo.module.v1.product.dto.UpdateProductDto;

import java.util.List;

public interface ProductService {
    List<ListProductDto> findAllProduct();
    DetailProductDto findProductById(Integer productId);
    Product createNewProduct(CreateProductDto createProductDtoPayload);
    Product updateProducts(Integer productId, UpdateProductDto updateProductDtoPayload);
    Product deleteProduct(Integer productId);
}
