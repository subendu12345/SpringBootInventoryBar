package com.prod.GreenValley.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionItemRequest {
    private Long transItemId;
    private String productName;
    private Integer size;
    private String extraSize;
    private Integer price;
    private Integer quantity;
    private Boolean isExtraItem;

}
