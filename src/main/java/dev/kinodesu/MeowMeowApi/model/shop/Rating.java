package dev.kinodesu.MeowMeowApi.model.shop;

import dev.kinodesu.MeowMeowApi.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Rating {
    private int id;
    private Product product;
    private int value;
    private String comment;

}
