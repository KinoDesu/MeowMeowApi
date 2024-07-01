package dev.kinodesu.MeowMeowApi.service;

import dev.kinodesu.MeowMeowApi.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<Product> getProductList();
    Page<Product> getProductPage(Pageable pageable);
    void changeProductStatus(Long productId);
}
