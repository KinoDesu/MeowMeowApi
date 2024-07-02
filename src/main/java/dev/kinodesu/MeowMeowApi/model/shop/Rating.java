package dev.kinodesu.MeowMeowApi.model.shop;

import dev.kinodesu.MeowMeowApi.model.ProductModel;
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
    private ProductModel product;
    private int value;
    private String comment;

}
