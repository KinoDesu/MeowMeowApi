package dev.kinodesu.MeowMeowApi.service.implementation;

import dev.kinodesu.MeowMeowApi.model.Product;
import dev.kinodesu.MeowMeowApi.service.ProductService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    public static List<Product> productList = new ArrayList<>();

    public ProductServiceImpl(){
        for(int i = 1;i<=50;i++){
            productList.add(new Product((long) i, "produto "+i, "produto "+i, i*7.35,Map.of(
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
        int start = (int) pageable.getOffset();
        int end = Math.min(start+pageable.getPageSize(),productList.size());
        List<Product> sublist = productList.subList(start,end);

        return new PageImpl<>(sublist,pageable, productList.size());
    }

    @Override
    public void changeProductStatus(Long productId) {
        Product product = productList.stream().filter(x-> Objects.equals(x.getId(), productId)).findFirst().get();
        product.setActive(!product.isActive());
    }
}
