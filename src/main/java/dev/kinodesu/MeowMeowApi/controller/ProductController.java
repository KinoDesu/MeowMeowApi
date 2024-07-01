package dev.kinodesu.MeowMeowApi.controller;

import dev.kinodesu.MeowMeowApi.model.Product;
import dev.kinodesu.MeowMeowApi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok().body(productList);
    }

    @GetMapping("page")
    public ResponseEntity<?> getPagedProductList(@PageableDefault(value = 10) Pageable pageable){
        Page<Product> page = productService.getProductPage(pageable);
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> changeProductStatus(@PathVariable("id") Long productId){
        productService.changeProductStatus(productId);
        return ResponseEntity.noContent().build();
    }

}
