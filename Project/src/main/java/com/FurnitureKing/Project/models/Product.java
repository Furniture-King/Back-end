package com.FurnitureKing.Project.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "product")
public class Product {
    @Id
    private String id;
    @Field
    private String name;
    @Field
    private String color;
    @Field
    private Integer stock;
    @Field
    private Integer star;
    @Field
    private Integer width;
    @Field
    private Integer length;

    public Product() {
    }

    public Product(
            String id,
            String name,
            String color,
            Integer stock,
            Integer star,
            Integer width,
            Integer length) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.stock = stock;
        this.star = star;
        this.width = width;
        this.length = length;
    }

    public Product(String name,
                   String color,
                   Integer stock,
                   Integer star,
                   Integer width,
                   Integer length) {
        this.name = name;
        this.color = color;
        this.stock = stock;
        this.star = star;
        this.width = width;
        this.length = length;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", stock=" + stock +
                ", star=" + star +
                ", width=" + width +
                ", length=" + length +
                '}';
    }
}
