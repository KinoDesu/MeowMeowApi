package dev.kinodesu.MeowMeowApi.service.implementation;

import dev.kinodesu.MeowMeowApi.model.Product;
import dev.kinodesu.MeowMeowApi.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> listProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1L, "produto 1", "produto 1", 12.50, Map.of(
                "Marca", "Samsung",
                "Modelo", "Galaxy S21",
                "Memória RAM", "8GB",
                "Armazenamento", "128GB"
        )));
        productList.add(new Product(2L, "produto 2", "produto 2", 12.50, Map.of(
                "Marca", "Dell",
                "Modelo", "XPS 13",
                "Processador", "Intel Core i7",
                "Tela", "13.3 polegadas"
        )));
        productList.add(new Product(3L, "produto 3", "produto 3", 12.50, Map.of(
                "Marca", "Canon",
                "Modelo", "EOS R5",
                "Resolução", "45MP",
                "Tipo de Sensor", "Full-Frame"
        )));
        productList.add(new Product(4L, "produto 4", "produto 4", 12.50, Map.of(
                "Marca", "LG",
                "Modelo", "InstaView Door-in-Door",
                "Capacidade", "668L",
                "Tipo", "Side-by-Side"
        )));

        return productList;
    }
}
