package dev.kinodesu.MeowMeowApi.model.stock;

import dev.kinodesu.MeowMeowApi.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Stock {
    private int id;
    private Product product;
    private long quantity;

}
