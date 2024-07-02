package dev.kinodesu.MeowMeowApi.model;

import dev.kinodesu.MeowMeowApi.model.shop.DiscountModel;
import dev.kinodesu.MeowMeowApi.model.stock.StockModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class ProductModel {

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

    // ? ObjectMapper => converter pra salver como json
    @Column(name = "product_details")
    private String details;
    /* private Map<String, String> details = new HashMap<>(); */

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_stock_id", referencedColumnName = "stock_id")
    private StockModel stock;

    @OneToOne
    @JoinColumn(name = "fk_discount_id", referencedColumnName = "discount_id")
    private DiscountModel discount;

    public ProductModel(DTOProductModel product) {
        this.id = null;
        this.name = product.name();
        this.description = product.description();
        this.price = product.price();
        this.details = product.details();
        this.stock = new StockModel(product.stock());
        this.discount = product.discount().id() == null ? null : new DiscountModel(product.discount());

    }

}
