package dev.kinodesu.MeowMeowApi.model;

import dev.kinodesu.MeowMeowApi.model.shop.Category;
import dev.kinodesu.MeowMeowApi.model.shop.Discount;
import dev.kinodesu.MeowMeowApi.model.shop.Rating;
import dev.kinodesu.MeowMeowApi.model.stock.Stock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    private Long id;
    private String name;
    private String description;
    private double price;
    private Map<String, String> details = new HashMap<>();
    private boolean isActive;
//    private Discount discount;
//    private Set<Category> categories = new HashSet<>();
//    private Set<Rating> ratings = new HashSet<>();
//    private Set<Image> images = new HashSet<>();
//    private Stock stock;
}
