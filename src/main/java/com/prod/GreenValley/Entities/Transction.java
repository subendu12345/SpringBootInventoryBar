package com.prod.GreenValley.Entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "transction")
@Data
public class Transction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "discount_amount")
    private BigDecimal discountAmount;

    @Column(name = "total_volume")
    private BigDecimal totalVolume;

    @Column(name = "table_no")
    private Integer tableNo ;

    @Column(name = "discount")
    private BigDecimal discount;

    @Temporal(TemporalType.DATE)
    @Column(name = "transction_date")
    private LocalDate transctionDate;

    @OneToMany(mappedBy = "transction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransctionItem> items;

    @PrePersist
    protected void onCreate() {
        this.transctionDate = LocalDate.now();
       
    }
}
