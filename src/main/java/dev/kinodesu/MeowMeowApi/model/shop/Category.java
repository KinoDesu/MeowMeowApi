package dev.kinodesu.MeowMeowApi.model.shop;

import dev.kinodesu.MeowMeowApi.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {
    private int id;
    private String name;
    private Set<Product> products = new HashSet<>();

}
