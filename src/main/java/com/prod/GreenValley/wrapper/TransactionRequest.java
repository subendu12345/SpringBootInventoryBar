package com.prod.GreenValley.wrapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    private Long transId;
    private Integer tableNo;
    private BigDecimal totalAmount;
    private BigDecimal discountAmount;
    private BigDecimal discount;
    private LocalDate transctionDate;
    private List<TransactionItemRequest> items;
    
}