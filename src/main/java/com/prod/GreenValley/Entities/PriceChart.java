package com.prod.GreenValley.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "price_chart")
@Data
public class PriceChart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "size")
    private Integer size;

    @Column(name = "price")
    private Integer price;

    @Column(name = "is_beer")
    private Boolean isBeer;
    
}
