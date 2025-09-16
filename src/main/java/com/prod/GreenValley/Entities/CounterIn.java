package com.prod.GreenValley.Entities;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name = "counter_in")
public class CounterIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "counter_in_date")
    private Date counterInDate;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "counterIn", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CounterInItem> CounterInItems = new ArrayList<>();
    
}
