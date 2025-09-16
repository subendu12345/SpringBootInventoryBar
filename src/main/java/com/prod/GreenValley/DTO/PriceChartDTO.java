package com.prod.GreenValley.DTO;

public class PriceChartDTO {
    private Long id;
    private String productName;
    private Integer size;
    private Integer price;


    public PriceChartDTO(Long id, String productName, Integer size, Integer price){
        this.id = id;
        this.productName = productName;
        this.size = size;
        this.price = price;
    }

    public PriceChartDTO(){}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Integer getSize() {
        return size;
    }
    public void setSize(Integer size) {
        this.size = size;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
}
