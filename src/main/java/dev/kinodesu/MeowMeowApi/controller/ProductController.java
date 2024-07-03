package dev.kinodesu.MeowMeowApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import dev.kinodesu.MeowMeowApi.model.DTOProduct;
import dev.kinodesu.MeowMeowApi.model.Product;
import dev.kinodesu.MeowMeowApi.service.ProductService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<?> getProductList() {
        List<Product> productList = productService.getProductList();

        if (productList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(productList);
    }

    @GetMapping("page")
    public ResponseEntity<?> getPagedProductList(@PageableDefault(value = 10) Pageable pageable){
        Page<Product> page = productService.getProductPage(pageable);

        if (page.getContent().isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> postProduct(@RequestBody DTOProduct product) {
        try {
            return new ResponseEntity<Product>(productService.postProduct(new Product(product)),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<?> putProductById(@PathVariable Long id, @RequestBody DTOProduct product) {
        try {
            return new ResponseEntity<Product>(productService.putProductById(id, new Product(product)),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<?> changeProductStatus(@PathVariable("id") Long productId){
        productService.changeProductStatus(productId);
        return ResponseEntity.noContent().build();
    }

}
