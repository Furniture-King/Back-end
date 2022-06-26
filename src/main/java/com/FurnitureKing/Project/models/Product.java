package com.FurnitureKing.Project.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "product")
public class Product {
    @Id
    private String id;
    @Field
    private String categoryName;
    @Field
    private String name;
    @Field
    private String color;
    @Field
    private List<String> srcImg;
    @Field
    private Integer stock;
    @Field
    private Float stars;
    @Field
    private Float width;
    @Field
    private Float length;
    @Field
    private Double price;
    @Field
    private String description;
    @Field
    private String desc1;
    @Field
    private String desc2;
    @Field
    private long createdAt;
    @Field
    private long updatedAt;

    public Product() {}

    public Product(String id, String categoryName, String name, String color, Integer stock, Float stars, Float width, Float length, Double price, String description, String desc1, String desc2) {
        this.id = id;
        this.categoryName = categoryName;
        this.name = name;
        this.color = color;
        this.stock = stock;
        this.stars = stars;
        this.width = width;
        this.length = length;
        this.price = price;
        this.description = description;
        this.desc1 = desc1;
        this.desc2 = desc2;
    }

    public Product(
            String id,
            String categoryName,
            String name,
            String color,
            List<String> srcImg,
            Integer stock,
            Float stars,
            Float width,
            Float length,
            Double price,
            String description,
            String desc1,
            String desc2,
            long createdAt,
            long updatedAt
    ) {
        this.id = id;
        this.categoryName = categoryName;
        this.name = name;
        this.color = color;
        this.srcImg = srcImg;
        this.stock = stock;
        this.stars = stars;
        this.width = width;
        this.length = length;
        this.price = price;
        this.description = description;
        this.desc1 = desc1;
        this.desc2 = desc2;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Product(
            String categoryName,
            String name,
            String color,
            List<String> srcImg,
            Integer stock,
            Float stars,
            Float width,
            Float length,
            Double price,
            String description,
            String desc1,
            String desc2,
            long createdAt,
            long updatedAt) {
        this.categoryName = categoryName;
        this.name = name;
        this.color = color;
        this.srcImg = srcImg;
        this.stock = stock;
        this.stars = stars;
        this.width = width;
        this.length = length;
        this.price = price;
        this.description = description;
        this.desc1 = desc1;
        this.desc2 = desc2;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public List<String> getSrcImg(){return srcImg;}

    public void setSrcImg(List<String> srcImg){this.srcImg = srcImg;}

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Float getStars() {
        return stars;
    }

    public void setStars(Float stars) {
        this.stars = stars;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    public String getDesc2() {
        return desc2;
    }

    public void setDesc2(String desc2) {
        this.desc2 = desc2;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", categoryName=" + categoryName +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", srcImg=" + srcImg +
                ", stock=" + stock +
                ", stars=" + stars +
                ", width=" + width +
                ", length=" + length +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", desc1='" + desc1 + '\'' +
                ", desc2='" + desc2 + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
