package dev.kinodesu.MeowMeowApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.kinodesu.MeowMeowApi.model.ProductModel;
import dev.kinodesu.MeowMeowApi.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductModel postProduct(ProductModel product) {
        return productRepository.save(product);
    }

    public ProductModel putProductById(Long id, ProductModel product) {
        product.setId(id);

        return productRepository.save(product);
    }

}
