package dev.kinodesu.MeowMeowApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.kinodesu.MeowMeowApi.model.ProductModel;
import dev.kinodesu.MeowMeowApi.service.ProductService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /* @GetMapping("all")
    public ResponseEntity<?> getProductList(Pageable pageable) {
        try {
            Page<ProductModel> products = productService.getProductList();

            if (products.isEmpty()) {
                return new ResponseEntity<>("No products found!", HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<Page<ProductModel>>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } */

    @GetMapping("{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        try {
            if (productService.getProductById(id) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            
            return new ResponseEntity<ProductModel>(productService.getProductById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

}
