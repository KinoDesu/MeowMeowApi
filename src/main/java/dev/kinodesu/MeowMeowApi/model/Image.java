package dev.kinodesu.MeowMeowApi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Image {
    private int id;
    private ProductModel product;
    private String type;
    private String name;
    private boolean isMain;
    private String url;

}
