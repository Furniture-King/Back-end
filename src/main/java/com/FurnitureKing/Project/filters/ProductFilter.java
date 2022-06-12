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
    private Double price;

    public String getCategoryName() {return categoryName;}

    public String getColor() {return color;}

    public Integer getStock() { return stock;}

    public Float getStars() {return stars;}

    public Double getPrice() {return price;}
}
