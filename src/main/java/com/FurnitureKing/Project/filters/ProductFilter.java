package com.FurnitureKing.Project.filters;

import org.springframework.data.mongodb.core.mapping.Field;

public class ProductFilter {
    @Field
    private String categoryName;
    @Field
    private String color;
    @Field
    private Integer stock;
    @Field
    private Float stars;
    @Field
    private Integer firstPrice;
    @Field
    private Integer secondPrice;

    public ProductFilter(String categoryName, String color, Integer stock, Float stars, Integer firstPrice, Integer secondPrice) {
        this.categoryName = categoryName;
        this.color = color;
        this.stock = stock;
        this.stars = stars;
        this.firstPrice = firstPrice;
        this.secondPrice = secondPrice;
    }

    public String getCategoryName() {return categoryName;}

    public String getColor() {return color;}

    public Integer getStock() { return stock;}

    public Float getStars() {return stars;}

    public Integer getfirstPrice() {return firstPrice;}

    public Integer getsecondPrice() {return secondPrice;}
}
