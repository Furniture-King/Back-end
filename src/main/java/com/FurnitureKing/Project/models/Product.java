package com.FurnitureKing.Project.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "product")
public class Product {
    @Id
    private ObjectId id;
    @Field
    private Integer category_id;
    @Field
    private String name;
    @Field
    private String color;
    @Field
    private String srcImg;
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
    private Date createdAt;
    @Field
    private Date updatedAt;

    public Product() {
    }

    public Product(
            ObjectId id,
            Integer category_id,
            String name,
            String color,
            String srcImg,
            Integer stock,
            Float stars,
            Float width,
            Float length,
            Double price,
            String description,
            String desc1,
            String desc2,
            Date createdAt,
            Date updatedAt
    ) {
        this.id = id;
        this.category_id = category_id;
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
            Integer category_id,
            String name,
            String color,
            String srcImg,
            Integer stock,
            Float stars,
            Float width,
            Float length,
            Double price,
            String description,
            String desc1,
            String desc2,
            Date createdAt,
            Date updatedAt) {
        this.category_id = category_id;
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

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
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

    public String getSrcImg(){return srcImg;}

    public void setSrcImg(String srcImg){this.srcImg = srcImg;}

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category_id=" + category_id +
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
