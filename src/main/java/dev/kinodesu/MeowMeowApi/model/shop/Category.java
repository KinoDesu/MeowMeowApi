package dev.kinodesu.MeowMeowApi.model.shop;

import dev.kinodesu.MeowMeowApi.model.ProductModel;
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
    private Set<ProductModel> products = new HashSet<>();

}
