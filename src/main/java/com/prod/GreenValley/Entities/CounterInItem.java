package com.prod.GreenValley.Entities;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "counter_in_item")
public class CounterInItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity_in")
    private Integer quantityIn ;

    @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "barcode")
    private String barcode ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "counter_in_id", nullable = false)
    private CounterIn counterIn;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
