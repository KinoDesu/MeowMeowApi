package dev.kinodesu.MeowMeowApi.service.implementation;

import dev.kinodesu.MeowMeowApi.model.Product;
import dev.kinodesu.MeowMeowApi.repository.ProductRepository;
import dev.kinodesu.MeowMeowApi.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product postProduct(Product product) {
        return productRepository.save(product);
    }


    @Override
    public Product putProductById(Long id, Product product) {
        product.setId(id);

        return productRepository.save(product);
    }
}
