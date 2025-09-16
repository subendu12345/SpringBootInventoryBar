package com.prod.GreenValley.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "transction_item")
@Data
public class TransctionItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "size")
    private Integer size;

    @Column(name = "extra_size")
    private String extraSize;

    @Column(name ="price")
    private Integer price;

    @Column(name="quantity")
    private Integer quantity;

    @Column(name="is_extra_item")
    private Boolean isExtraItem;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transction_id", nullable = false)
    private Transction transction;
}
