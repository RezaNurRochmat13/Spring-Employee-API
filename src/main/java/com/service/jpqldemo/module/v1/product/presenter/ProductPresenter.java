package com.service.jpqldemo.module.v1.product.presenter;

import com.service.jpqldemo.module.v1.product.dao.Product;
import com.service.jpqldemo.module.v1.product.dto.CreateProductDto;
import com.service.jpqldemo.module.v1.product.dto.DetailProductDto;
import com.service.jpqldemo.module.v1.product.dto.ListProductDto;
import com.service.jpqldemo.module.v1.product.dto.UpdateProductDto;
import com.service.jpqldemo.module.v1.product.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class ProductPresenter {

    @Autowired
    ProductServiceImpl productService;

    @GetMapping("products")
    public Map<String, Object> getAllProducts() {
        Map<String, Object> map = new HashMap<>();
        List<ListProductDto> productList = productService.findAllProduct();
        map.put("count", productList.size());
        map.put("data", productList);
        return map;
    }

    @GetMapping("product/{id}")
    public Map<String, Object> getDetailProduct(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        DetailProductDto detailProductDto = productService.findProductById(id);
        map.put("data", detailProductDto);
        return map;

    }

    @PostMapping("product")
    public Map<String, Object> createNewProducts(@Valid @RequestBody CreateProductDto createProductDtoPayload) {
        Map<String, Object> map = new HashMap<>();
        Product product = productService.createNewProduct(createProductDtoPayload);

        map.put("message", "Product created successfully");
        map.put("created_products", product);
        return map;

    }

    @PutMapping("product/{id}")
    public Map<String, Object> updateProduct(@PathVariable Integer id,
                                             @RequestBody UpdateProductDto updateProductDtoPayload) {
        Map<String, Object> map = new HashMap<>();
        Product product = productService.updateProducts(id, updateProductDtoPayload);
        map.put("message", "Product updated successfully");
        map.put("updated_product", product);
        return map;
    }

    // Delete product on going
    @DeleteMapping("product/{id}")
    public Map<String, Object> deleteProducts(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        Product product = productService.deleteProduct(id);

        map.put("message", "Product deleted successfully");
        map.put("deleted_product", product);
        return map;
    }
}
