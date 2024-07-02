package dev.kinodesu.MeowMeowApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.kinodesu.MeowMeowApi.model.DTOProductModel;
import dev.kinodesu.MeowMeowApi.model.Product;
import dev.kinodesu.MeowMeowApi.model.ProductModel;
import dev.kinodesu.MeowMeowApi.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<?> getProductList() {
        List<Product> productList = productService.getProductList();
        return ResponseEntity.ok().body(productList);
    }

    @GetMapping("page")
    public ResponseEntity<?> getPagedProductList(@PageableDefault(value = 10) Pageable pageable) {
        Page<Product> page = productService.getProductPage(pageable);
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<?> changeProductStatus(@PathVariable("id") Long productId) {
        productService.changeProductStatus(productId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> postProduct(@RequestBody DTOProductModel product) {
        try {
            return new ResponseEntity<ProductModel>(productService.postProduct(new ProductModel(product)),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<?> putProductById(@PathVariable Long id, @RequestBody DTOProductModel product) {
        try {
            return new ResponseEntity<ProductModel>(productService.putProductById(id, new ProductModel(product)),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
