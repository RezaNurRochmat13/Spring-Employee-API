package com.service.jpqldemo.module.v1.product.service;

import com.service.jpqldemo.config.ModelMapperConfig;
import com.service.jpqldemo.module.v1.product.dao.Product;
import com.service.jpqldemo.module.v1.category_product.dao.ProductCategory;
import com.service.jpqldemo.module.v1.product.dto.CreateProductDto;
import com.service.jpqldemo.module.v1.product.dto.DetailProductDto;
import com.service.jpqldemo.module.v1.product.dto.ListProductDto;
import com.service.jpqldemo.module.v1.product.dto.UpdateProductDto;
import com.service.jpqldemo.exception.ResourceNotFound;
import com.service.jpqldemo.module.v1.category_product.repository.CategoryProductRepository;
import com.service.jpqldemo.module.v1.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryProductRepository categoryProductRepository;

    @Autowired
    ModelMapperConfig modelMapperConfig;

    @Override
    public List<ListProductDto> findAllProduct() {
        List<Product> productList = productRepository.findAllProductWithJoins();
        Long countAllProduct = productRepository.count();
        List<ListProductDto> productDtos = new ArrayList<>();

        for(Product product: productList) {
            ListProductDto listProductDto = modelMapperConfig.modelMapper().map(product, ListProductDto.class);
            listProductDto.setProductId(product.getId());
            listProductDto.setProductName(product.getProductName());
            listProductDto.setProductSeller(product.getProductSeller());
            listProductDto.setCategoryName(product.getProductCategory().getProductCategoryName());
            productDtos.add(listProductDto);
        }

        return productDtos;
    }

    @Override
    public DetailProductDto findProductById(Integer productId) {
        Optional<Product> product = Optional.ofNullable(productRepository.findProductByIdNative(productId)
                .orElseThrow(() -> new ResourceNotFound("Products not found with id : " + productId)));
        DetailProductDto detailProductDto = modelMapperConfig
                .modelMapper().map(product, DetailProductDto.class);

        product.ifPresent(results -> {
            detailProductDto.setProductId(results.getId());
            detailProductDto.setProductName(results.getProductName());
            detailProductDto.setProductSeller(results.getProductSeller());
            detailProductDto.setProductDescription(results.getProductDescription());
            detailProductDto.setCategoryName(results.getProductCategory().getProductCategoryName());
        });

        return detailProductDto;
    }

    @Override
    public Product createNewProduct(CreateProductDto createProductDtoPayload) {
        Product product = modelMapperConfig.modelMapper().map(createProductDtoPayload, Product.class);
        ProductCategory productCategory = categoryProductRepository.findById(createProductDtoPayload.getCategoryProduct())
                .orElseThrow(() -> new ResourceNotFound("Category product not found with id : " + createProductDtoPayload.getCategoryProduct()));

        product.setProductName(createProductDtoPayload.getProductName());
        product.setProductSeller(createProductDtoPayload.getProductSeller());
        product.setProductDescription(createProductDtoPayload.getProductDescription());
        product.setProductCategory(productCategory);

        productRepository.save(product);
        return product;
    }

    @Override
    public Product updateProducts(Integer productId, UpdateProductDto updateProductDtoPayload) {
        Product product = productRepository.findProductByIdNative(productId)
                .orElseThrow(() -> new ResourceNotFound("Product not found with id : " + productId));
        ProductCategory productCategory = categoryProductRepository.findById(updateProductDtoPayload.getCategoryProduct())
                .orElseThrow(() -> new ResourceNotFound("Category product not found with id :" + updateProductDtoPayload.getCategoryProduct()));

        product.setProductName(updateProductDtoPayload.getProductName());
        product.setProductSeller(updateProductDtoPayload.getProductSeller());
        product.setProductDescription(updateProductDtoPayload.getProductDescription());
        product.setProductCategory(productCategory);
        productRepository.save(product);

        return product;
    }

    @Override
    public Product deleteProduct(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFound("Product not found with id : " + productId));
        productRepository.delete(product);

        return product;
    }
}
