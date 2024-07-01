package dev.kinodesu.MeowMeowApi.service.implementation;

import dev.kinodesu.MeowMeowApi.model.Product;
import dev.kinodesu.MeowMeowApi.service.ProductService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    public static List<Product> productList = new ArrayList<>();

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
        return productList;
    }

    @Override
    public Page<Product> getProductPage(Pageable pageable) {
        List<Product> sortedProducts = productList.stream().sorted(getComparator(pageable.getSort())).collect(Collectors.toList());
        int start = (int) pageable.getOffset();
        int end = Math.min(start+pageable.getPageSize(),sortedProducts.size());
        List<Product> sublist = sortedProducts.subList(start,end);



        return new PageImpl<>(sublist,pageable, productList.size());
    }

    @Override
    public void changeProductStatus(Long productId) {
        Product product = productList.stream().filter(x-> Objects.equals(x.getId(), productId)).findFirst().get();
        product.setActive(!product.isActive());
    }

    private Comparator<Product> getComparator(Sort sort) {
        Comparator<Product> comparator = Comparator.comparing(produto -> {
            Sort.Order order = sort.iterator().next();
            switch (order.getProperty()) {
                case "id":
                    return (Comparable) produto.getId();
                case "name":
                    return (Comparable) produto.getName();
                case "price":
                    return (Comparable) produto.getPrice();
                case "isActive":
                    return (Comparable) produto.isActive();
                default:
                    throw new IllegalArgumentException("Propriedade desconhecida: " + order.getProperty());
            }
        });

        if (sort.iterator().next().getDirection() == Sort.Direction.DESC) {
            comparator = comparator.reversed();
        }

        return comparator;
    }
}
