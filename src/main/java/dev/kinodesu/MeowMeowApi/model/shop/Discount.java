package dev.kinodesu.MeowMeowApi.model.shop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Discount {
    private int id;
    private String description;
    private double percentage;
    private LocalDate startDate;
    private LocalDate endDate;


}
