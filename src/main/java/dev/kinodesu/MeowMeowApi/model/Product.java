package dev.kinodesu.MeowMeowApi.model;

import dev.kinodesu.MeowMeowApi.model.shop.Discount;
import dev.kinodesu.MeowMeowApi.model.stock.Stock;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Entity(name = "Product")
@Table(name = "product")
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
    private Double price;

    @ElementCollection
    @CollectionTable(name = "product_details", joinColumns = @JoinColumn(name = "fk_product_id"))
    @MapKeyColumn(name = "detail_key")
    @Column(name = "detail_value")
    private Map<String, String> details = new HashMap<>();

    @Column(name = "product_active")
    private Boolean active;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_stock_id", referencedColumnName = "stock_id")
    private Stock stock;

    @OneToOne
    @JoinColumn(name = "fk_discount_id", referencedColumnName = "discount_id")
    private Discount discount;

//    private Set<Category> categories = new HashSet<>();
//    private Set<Rating> ratings = new HashSet<>();
//    private Set<Image> images = new HashSet<>();

    public Product(DTOProduct product) {
        this.id = null;
        this.name = product.name();
        this.description = product.description();
        this.price = product.price();
        this.details = product.details();
        this.active = product.active();
        this.stock = new Stock(product.stock());
        this.discount = product.discount().id() == null ? null : new Discount(product.discount());
    }
}
