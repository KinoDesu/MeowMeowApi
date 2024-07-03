package dev.kinodesu.MeowMeowApi.model;

import java.util.Map;

import dev.kinodesu.MeowMeowApi.model.shop.DTODiscount;
import dev.kinodesu.MeowMeowApi.model.stock.DTOStock;

public record DTOProduct(
        String name,
        String description,
        Double price,
        Map<String, String> details,
        Boolean active,
        DTOStock stock,
        DTODiscount discount) {

}
