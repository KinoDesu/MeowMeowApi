package dev.kinodesu.MeowMeowApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.kinodesu.MeowMeowApi.model.DTOProduct;
import dev.kinodesu.MeowMeowApi.model.Product;
import dev.kinodesu.MeowMeowApi.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService productService;

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

}
