package dev.kinodesu.MeowMeowApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.kinodesu.MeowMeowApi.model.DTOProductModel;
import dev.kinodesu.MeowMeowApi.model.ProductModel;
import dev.kinodesu.MeowMeowApi.service.ProductService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

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
