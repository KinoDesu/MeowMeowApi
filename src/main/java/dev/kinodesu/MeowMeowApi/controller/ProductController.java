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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Product responseProduct = productService.getProductById(id);
        if (responseProduct == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(responseProduct);
    }

    @GetMapping("page")
    public ResponseEntity<?> getPagedProductList(@PageableDefault(value = 10) Pageable pageable) {
        Page<Product> page = productService.getProductPage(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("filter")
    public ResponseEntity<?> getProductByDetail(@PageableDefault(value = 10) Pageable pageable, @RequestParam Map<String, String> filters) {
        Page<Product> page = productService.getFilteredProductPage(pageable, filters);

        return ResponseEntity.ok().body(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> postProduct(@RequestBody DTOProduct product) {
        try {

            Product Responseproduct = productService.postProduct(new Product(product));

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(Responseproduct.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<?> putProductById(@PathVariable Long id, @RequestBody DTOProduct product) {
        try {
            return new ResponseEntity<>(productService.putProductById(id, new Product(product)),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<?> changeProductStatus(@PathVariable("id") Long productId) {
        productService.changeProductStatus(productId);
        return ResponseEntity.noContent().build();
    }

}
