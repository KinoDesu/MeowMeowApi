package dev.kinodesu.MeowMeowApi.service;

import dev.kinodesu.MeowMeowApi.model.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    Product postProduct(Product product);

    Product putProductById(Long id, Product product);
}
