package dev.kinodesu.MeowMeowApi.controller;

import dev.kinodesu.MeowMeowApi.model.DTOProduct;
import dev.kinodesu.MeowMeowApi.model.Product;
import dev.kinodesu.MeowMeowApi.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Tag(name = "Produto")
@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<Object> getProductList() {
        List<Product> productList = productService.getProductList();
        return ResponseEntity.ok().body(productList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id) {
        Product responseProduct = productService.getProductById(id);
        if (responseProduct == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(responseProduct);
    }

    @GetMapping("page")
    public ResponseEntity<Object> getPagedProductList(@PageableDefault(value = 10) Pageable pageable) {
        Page<Product> page = productService.getProductPage(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("filter")
    public ResponseEntity<Object> getProductByDetail(@PageableDefault(value = 10) Pageable pageable, @RequestParam Map<String, String> filters) {
        Page<Product> page = productService.getFilteredProductPage(pageable, filters);

        return ResponseEntity.ok().body(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> postProduct(@RequestBody DTOProduct product) {
        try {

            Product responseproduct = productService.postProduct(new Product(product));

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(responseproduct.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<Object> putProductById(@PathVariable Long id, @RequestBody DTOProduct product) {
        try {
            return new ResponseEntity<>(productService.putProductById(id, new Product(product)),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Object> changeProductStatus(@PathVariable("id") Long productId) {
        productService.changeProductStatus(productId);
        return ResponseEntity.noContent().build();
    }

}
