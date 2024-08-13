package dev.kinodesu.MeowMeowApi.service.implementation;


import dev.kinodesu.MeowMeowApi.model.Product;
import dev.kinodesu.MeowMeowApi.repository.ProductRepository;
import dev.kinodesu.MeowMeowApi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.Comparator;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
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
    public Page<Product> getFilteredProductPage(Pageable pageable, Map<String, String> filters) {
        filters.remove("sort");
        filters.remove("page");
        filters.remove("size");

        Set<Product> products = new HashSet<>();
        List<String> priceRange = new ArrayList<>();

        if (filters.get("preco") != null) {
            try {
                priceRange = Arrays.stream(filters.get("preco").split("\\|")).toList();
                filters.remove("preco");
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

        int i = 0;
        do {
            Map.Entry<String, String> filter;
            if (!filters.isEmpty()) {
                filter = filters.entrySet().stream().toList().get(i);
            } else {
                filters.put("nome", "");
                filter = filters.entrySet().stream().toList().get(i);
            }


            List<String> filterValues = Arrays.stream(filter.getValue().split("\\|")).toList();
            for (String value : filterValues) {
                Page<Product> filteredPage;
                try {
                    filteredPage = productRepository.findProductByFilters(pageable, filter.getKey(), value, Double.parseDouble(priceRange.get(0)), Double.parseDouble(priceRange.get(1)), filters.get("nome"));
                } catch (Exception ex) {
                    pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Order.desc("id")));
                    filteredPage = productRepository.findProductByFilters(pageable, filter.getKey(), value, Double.parseDouble(priceRange.get(0)), Double.parseDouble(priceRange.get(1)), filters.get("nome"));
                }

                if (!filteredPage.isEmpty()) {
                    products.addAll(filteredPage.getContent());
                }
            }

            i++;
        } while (i < filters.size());

        Sort sort = pageable.getSort();
        if (sort.isUnsorted()) {
            return new PageImpl<>(products.stream().toList(), pageable, products.size());
        } else {
            Comparator<Product> comparator = sort.stream()
                    .map(order -> {
                        Comparator<Product> orderComparator;
                        if (order.getProperty().equals("id")) {
                            orderComparator = Comparator.comparing(Product::getId);
                        } else if (order.getProperty().equals("name")) {
                            orderComparator = Comparator.comparing(Product::getName);
                        } else if (order.getProperty().equals("price")) {
                            orderComparator = Comparator.comparing(Product::getPrice);
                        } else {
                            orderComparator = Comparator.comparing(Product::getId);
                        }

                        return order.isAscending() ? orderComparator : orderComparator.reversed();
                    })
                    .reduce(Comparator::thenComparing)
                    .orElseThrow(IllegalStateException::new);

            return new PageImpl<>(products.stream().sorted(comparator).toList(), pageable, products.size());
        }


    }

    @Override
    public void changeProductStatus(Long productId) {
        Product product = productRepository.findById(productId).get();
        product.setActive(!product.getActive());
        productRepository.save(product);
    }

    @Override
    public Product postProduct(Product product) {
        Map<String, String> details = product.getDetails();
        details.put("nome", product.getName());
        product.setDetails(details);
        return productRepository.save(product);
    }


    @Override
    public Product putProductById(Long id, Product product) {
        product.setId(id);

        return productRepository.save(product);
    }
}
