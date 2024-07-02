package dev.kinodesu.MeowMeowApi.service;

import dev.kinodesu.MeowMeowApi.model.Product;
import dev.kinodesu.MeowMeowApi.model.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Product> getProductList();

    Page<Product> getProductPage(Pageable pageable);

    void changeProductStatus(Long productId);

    ProductModel postProduct(ProductModel product);

    ProductModel putProductById(Long id, ProductModel product);
}
