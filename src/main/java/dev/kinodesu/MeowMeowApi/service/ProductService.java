package dev.kinodesu.MeowMeowApi.service;

import dev.kinodesu.MeowMeowApi.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Product> getProductList();

    Page<Product> getProductPage(Pageable pageable);

    void changeProductStatus(Long productId);

    Product postProduct(Product product);

    Product putProductById(Long id, Product product);
}
