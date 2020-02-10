package com.service.jpqldemo.module.v1.category_product.presenter;

import com.service.jpqldemo.module.v1.category_product.dao.ProductCategory;
import com.service.jpqldemo.module.v1.category_product.service.CategoryProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class CategoryProductPresenter {

    @Autowired
    CategoryProductServiceImpl categoryProductService;

    @GetMapping("category-products")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getAllCategoryProducts() {
        Map<String, Object> map = new HashMap<>();
        List<ProductCategory> productCategoryList = categoryProductService.findAllCategoryProduct();
        Long countAllCategory = categoryProductService.countAllCategoryProduct();
        map.put("count", productCategoryList.size());
        map.put("total", countAllCategory);
        map.put("data", productCategoryList);
        return map;
    }

    @GetMapping("category-product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getDetailCategoryProduct(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        Optional<ProductCategory> productCategory = categoryProductService.findCategoryProductById(id);
        map.put("data", productCategory);
        return map;
    }

    @PostMapping("category-product")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> createNewCategoryProduct(
            @Valid
            @RequestBody ProductCategory productCategoryPayload) {
        Map<String, Object> map = new HashMap<>();
        ProductCategory productCategory = categoryProductService
                .createNewProductCategory(productCategoryPayload);
        map.put("message", "Category product created successfully");
        map.put("created_category_product", productCategory);
        return map;
    }

    @PutMapping("category-product/{id}")
    public Map<String, Object> updateCategoryProduct(@Valid
                                                     @RequestBody ProductCategory productCategoryUpdatePayload,
                                                     @PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        ProductCategory productCategoryUpdate = categoryProductService
                .updateCategoryProduct(id, productCategoryUpdatePayload);
        map.put("message", "Category product updated successfully");
        map.put("updated_category_product", productCategoryUpdate);
        return map;

    }

    @DeleteMapping("category-product/{id}")
    public Map<String, Object> deleteCategoryProduct(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        ProductCategory productCategoryDelete = categoryProductService.deleteCategoryProduct(id);

        map.put("message", "Category product deleted successfully");
        map.put("deleted_category_product", productCategoryDelete);
        return map;
    }

}
