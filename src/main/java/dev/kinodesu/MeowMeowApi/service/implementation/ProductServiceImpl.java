package dev.kinodesu.MeowMeowApi.service.implementation;


import dev.kinodesu.MeowMeowApi.model.Product;
import dev.kinodesu.MeowMeowApi.repository.ProductRepository;
import dev.kinodesu.MeowMeowApi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;

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
    public Page<Product> getFilteredProductPage(Pageable pageable, Map<String, String> filters) {
        Set<Product> products = new HashSet<>();
        List<String> priceRange = new ArrayList<>();

        if (filters.get("preco") != null) {
            try {
                priceRange = Arrays.stream(filters.get("preco").split("\\|")).toList();
                if (Double.parseDouble(priceRange.get(0)) > Double.parseDouble(priceRange.get(1))) {
                    return null;
                }

                if (Double.parseDouble(priceRange.get(1)) == 0) {
                    priceRange.remove(1);
                    priceRange.add("9999999999");
                }
            } catch (Exception e) {
                if (!priceRange.isEmpty()) {
                    priceRange.clear();
                }
                priceRange.add("0");
                priceRange.add("9999999999");
            }
        } else {
            priceRange.add("0");
            priceRange.add("9999999999");
        }

        for (Map.Entry<String, String> filter : filters.entrySet()) {

            List<String> filterValues = Arrays.stream(filter.getValue().split("\\|")).toList();

            for (String value : filterValues) {
                List<Product> filteredPage = productRepository.findProductByFilters(filter.getKey(), value, Double.parseDouble(priceRange.get(0)), Double.parseDouble(priceRange.get(1)));

                if (!filteredPage.isEmpty()) {
                    products.addAll(filteredPage);
                }
            }
        }

        return new PageImpl<>(products.stream().toList(), pageable, products.size());
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
