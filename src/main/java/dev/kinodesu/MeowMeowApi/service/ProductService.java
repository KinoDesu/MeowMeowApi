package dev.kinodesu.MeowMeowApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.kinodesu.MeowMeowApi.model.ProductModel;
import dev.kinodesu.MeowMeowApi.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /* public Page<ProductModel> getProductList(Pageable pageable) {
        try {
            return productRepository.findAll(pageable);
        } catch (Exception e) {
            throw e;
        }
    } */

    public ProductModel getProductById(Long id){
        return productRepository.findProductByProductId(id);
    }

}
