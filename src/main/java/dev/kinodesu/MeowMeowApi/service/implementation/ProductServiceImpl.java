package dev.kinodesu.MeowMeowApi.service.implementation;

import dev.kinodesu.MeowMeowApi.model.Product;
import dev.kinodesu.MeowMeowApi.repository.ProductRepository;
import dev.kinodesu.MeowMeowApi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    public static List<Product> productList = new ArrayList<>();

    @Autowired
    private ProductRepository productRepository;

    public ProductServiceImpl(){
        for(int i = 1;i<=50;i++){
            productList.add(new Product((long) i, "produto "+String.format("%2s",i).replace(' ','0'), "produto "+i, i*7.35,Map.of(
                    "Marca", "Samsung",
                    "Modelo", "Galaxy S21",
                    "Memória RAM", "8GB",
                    "Armazenamento", "128GB"
            ),true));
        }
    }


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
        product.setActive(!product.isActive());
        productRepository.save(product);
    }
}
