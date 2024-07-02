package dev.kinodesu.MeowMeowApi.model;

import dev.kinodesu.MeowMeowApi.model.shop.Category;
import dev.kinodesu.MeowMeowApi.model.shop.Discount;
import dev.kinodesu.MeowMeowApi.model.shop.Rating;
import dev.kinodesu.MeowMeowApi.model.stock.Stock;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity(name="Product")
@Table(name = "Product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_description")
    private String description;

    @Column(name = "product_price")
    private double price;

    @ElementCollection
    @CollectionTable(name = "product_details", joinColumns = @JoinColumn(name = "fk_product_id"))
    @MapKeyColumn(name = "detail_key")
    @Column(name = "detail_value")
    private Map<String, String> details = new HashMap<>();

    @Column(name = "product_active")
    private boolean isActive;
//    private Discount discount;
//    private Set<Category> categories = new HashSet<>();
//    private Set<Rating> ratings = new HashSet<>();
//    private Set<Image> images = new HashSet<>();
//    private Stock stock;
}
