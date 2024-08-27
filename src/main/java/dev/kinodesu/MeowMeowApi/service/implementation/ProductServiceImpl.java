package dev.kinodesu.MeowMeowApi.service.implementation;


import dev.kinodesu.MeowMeowApi.model.Product;
import dev.kinodesu.MeowMeowApi.repository.ProductRepository;
import dev.kinodesu.MeowMeowApi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private static final String PRICE_KEY = "preco";
    private static final String SORT_KEY = "sort";
    private static final String PAGE_KEY = "page";
    private static final String SIZE_KEY = "size";
    private static final String NAME_KEY = "nome";
    private static final String ID_KEY = "id";

    private static final String MAX_PRICE_RANGE = "9999999999";
    private static final String MIN_PRICE_RANGE = "0";

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
        filters.remove(SORT_KEY);
        filters.remove(PAGE_KEY);
        filters.remove(SIZE_KEY);

        Set<Product> products = new HashSet<>();

        String[] priceRange = priceFilter(filters);

        if (filters.isEmpty()) {
            filters.put(NAME_KEY, "");
        }

        pageable = findProductsByFilter(pageable, filters, priceRange, products);

        return sortResult(pageable, products);
    }

    private Pageable findProductsByFilter(Pageable pageable, Map<String, String> filters, String[] priceRange, Set<Product> products) {
        for (Map.Entry<String, String> filter : filters.entrySet()) {

            String filterKey = filter.getKey();
            List<String> filterValues = Arrays.stream(filter.getValue().split("\\|")).toList();

            double minPriceValue = Double.parseDouble(priceRange[0]);
            double maxPriceValue = Double.parseDouble(priceRange[1]);
            String nameFilter = filters.get(NAME_KEY);

            for (String filterValue : filterValues) {
                Page<Product> filteredPage;


                try {
                    filteredPage = productRepository.findProductByFilters(pageable, filterKey, filterValue, minPriceValue, maxPriceValue, nameFilter);
                } catch (Exception ex) {
                    pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Order.desc(ID_KEY)));
                    filteredPage = productRepository.findProductByFilters(pageable, filterKey, filterValue, minPriceValue, maxPriceValue, nameFilter);
                }

                if (!filteredPage.isEmpty()) {
                    products.addAll(filteredPage.getContent());
                }
            }

        }
        return pageable;
    }

    private PageImpl<Product> sortResult(Pageable pageable, Set<Product> products) {
        Sort sort = pageable.getSort();
        if (sort.isUnsorted()) {
            return new PageImpl<>(products.stream().toList(), pageable, products.size());
        } else {
            Comparator<Product> comparator = sort.stream()
                    .map(order -> {
                        Comparator<Product> orderComparator;
                        if (order.getProperty().equals(ID_KEY)) {
                            orderComparator = Comparator.comparing(Product::getId);
                        } else if (order.getProperty().equals(translateKey(NAME_KEY))) {
                            orderComparator = Comparator.comparing(Product::getName);
                        } else if (order.getProperty().equals(translateKey(PRICE_KEY))) {
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

    private String translateKey(String key) {
        Map<String, String> dictionary = new HashMap<>();
        dictionary.put(NAME_KEY, "name");
        dictionary.put(PRICE_KEY, "price");

        return dictionary.get(key);
    }

    private String[] priceFilter(Map<String, String> filters) {
        String[] priceRange = new String[2];

        if (filters.get(PRICE_KEY) == null) {

            priceRange[0] = MIN_PRICE_RANGE;
            priceRange[1] = MAX_PRICE_RANGE;

        } else {

            try {
                priceRange = filters.get(PRICE_KEY).split("\\|");
                filters.remove(PRICE_KEY);

                double maxValue = Double.parseDouble(priceRange[1]);
                double minValue = Double.parseDouble(priceRange[0]);

                if (maxValue <= 0.00) {
                    priceRange[1] = MAX_PRICE_RANGE;
                }

                if (minValue > maxValue) {
                    priceRange[0] = String.valueOf(minValue + 1);
                }

            } catch (Exception e) {
                priceRange[0] = MIN_PRICE_RANGE;
                priceRange[1] = MAX_PRICE_RANGE;
            }

        }

        return priceRange;
    }

    @Override
    public void changeProductStatus(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            return;
        }
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
