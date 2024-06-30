package dev.kinodesu.MeowMeowApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.kinodesu.MeowMeowApi.model.ProductModel;
import dev.kinodesu.MeowMeowApi.model.stock.StockModel;
import dev.kinodesu.MeowMeowApi.repository.ProductRepository;
import dev.kinodesu.MeowMeowApi.repository.StockRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    public ProductModel postProduct(ProductModel product) {
        StockModel stock = stockRepository.save(product.getStock());

        product.setStock(stock);

        return productRepository.save(product);
    }

}
