package dev.kinodesu.MeowMeowApi.model;

import dev.kinodesu.MeowMeowApi.model.shop.DTODiscount;
import dev.kinodesu.MeowMeowApi.model.stock.DTOStock;

public record DTOProductModel(
        String name,
        String description,
        Double price,
        String details,
        DTOStock stock,
        DTODiscount discount) {

}
