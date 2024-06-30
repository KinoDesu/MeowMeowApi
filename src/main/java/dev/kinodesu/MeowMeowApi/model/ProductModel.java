package dev.kinodesu.MeowMeowApi.model;

import dev.kinodesu.MeowMeowApi.model.shop.Discount;
import dev.kinodesu.MeowMeowApi.model.stock.Stock;
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
    
    // ! Não vai funfar do jeito q a gente quer, com HashMap é (identificador, valor)
    @Column(name = "product_details")
    private Map<String, String> details = new HashMap<>();
    
    @OneToOne
    @JoinColumn(name = "fk_stock_id", referencedColumnName = "stock_id")
    private Stock stock;
    
    @OneToOne
    @JoinColumn(name = "fk_discount_id", referencedColumnName = "discount_id")
    private Discount discount;

}
