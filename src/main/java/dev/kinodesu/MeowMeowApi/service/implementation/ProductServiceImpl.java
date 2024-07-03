package dev.kinodesu.MeowMeowApi.service.implementation;


import dev.kinodesu.MeowMeowApi.model.Product;
import dev.kinodesu.MeowMeowApi.repository.ProductRepository;
import dev.kinodesu.MeowMeowApi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProductList() {
        return productRepository.findAllByOrderByIdDesc();
    }

    @Override
    public Page<Product> getProductPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public void changeProductStatus(Long productId) {
        Product product = productRepository.findById(productId).get();
        product.setActive(!product.getActive());
        productRepository.save(product);
    }

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
