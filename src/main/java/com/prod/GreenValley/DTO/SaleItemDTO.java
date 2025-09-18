package com.prod.GreenValley.DTO;

import java.math.BigDecimal;

public class SaleItemDTO {
    private Long saleItemId;
    private Integer quantitySold ;
    private BigDecimal unitPriceAtSale;
    private String productInfo;
    private String productName;
    private Integer volumnML;
    public Long getSaleItemId() {
        return saleItemId;
    }
    public void setSaleItemId(Long saleItemId) {
        this.saleItemId = saleItemId;
    }
    public Integer getQuantitySold() {
        return quantitySold;
    }
    public void setQuantitySold(Integer quantitySold) {
        this.quantitySold = quantitySold;
    }
    public BigDecimal getUnitPriceAtSale() {
        return unitPriceAtSale;
    }
    public void setUnitPriceAtSale(BigDecimal unitPriceAtSale) {
        this.unitPriceAtSale = unitPriceAtSale;
    }
    public String getProductInfo() {
        return productInfo;
    }
    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Integer getVolumnML() {
        return volumnML;
    }
    public void setVolumnML(Integer volumnML) {
        this.volumnML = volumnML;
    }


    

    
}
