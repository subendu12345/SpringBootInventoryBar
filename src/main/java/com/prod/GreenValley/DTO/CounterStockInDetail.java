package com.prod.GreenValley.DTO;

import java.math.BigDecimal;

public record CounterStockInDetail (
    String productName,
    BigDecimal totalCounterVolume
){
    
}
