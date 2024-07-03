package dev.kinodesu.MeowMeowApi.model;

import dev.kinodesu.MeowMeowApi.model.shop.Discount;
import dev.kinodesu.MeowMeowApi.model.stock.Stock;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    private Boolean isActive;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_stock_id", referencedColumnName = "stock_id")
    private Stock stock;

    @OneToOne
    @JoinColumn(name = "fk_discount_id", referencedColumnName = "discount_id")
    private Discount discount;

    public Product(DTOProduct product) {
        this.id = null;
        this.name = product.name();
        this.description = product.description();
        this.price = product.price();
        this.details = product.details();
        this.isActive = product.isActive();
        this.stock = new Stock(product.stock());
        this.discount = product.discount().id() == null ? null : new Discount(product.discount());
    }

}
