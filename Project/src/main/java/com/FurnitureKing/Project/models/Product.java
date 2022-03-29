package com.FurnitureKing.Project.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.lang.reflect.Array;
import java.util.Date;

@Document(collection = "product")
public class Product {
    @Id
    private ObjectId id;
    @Field
    private ObjectId category_id;
    @Field
    private String name;
    @Field
    private String color;
    @Field
    private Integer stock;
    @Field
    private Array fav;
    @Field
    private Integer star;
    @Field
    private Integer width;
    @Field
    private Integer length;
    @Field
    private Integer category;
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
            ObjectId category_id,
            String name,
            String color,
            Integer stock,
            Array fav,
            Integer star,
            Integer width,
            Integer length,
            Integer category,
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
        this.stock = stock;
        this.fav = fav;
        this.star = star;
        this.width = width;
        this.length = length;
        this.category = category;
        this.price = price;
        this.description = description;
        this.desc1 = desc1;
        this.desc2 = desc2;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Product(
            ObjectId category_id,
            String name,
            String color,
            Integer stock,
            Array fav,
            Integer star,
            Integer width,
            Integer length,
            Integer category,
            Double price,
            String description,
            String desc1,
            String desc2,
            Date createdAt,
            Date updatedAt) {
        this.category_id = category_id;
        this.name = name;
        this.color = color;
        this.stock = stock;
        this.fav = fav;
        this.star = star;
        this.width = width;
        this.length = length;
        this.category = category;
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

    public ObjectId getCategory_id() {
        return category_id;
    }

    public void setCategory_id(ObjectId category_id) {
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Array getFav() {
        return fav;
    }

    public void setFav(Array fav) {
        this.fav = fav;
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

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
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
                ", stock=" + stock +
                ", fav=" + fav +
                ", star=" + star +
                ", width=" + width +
                ", length=" + length +
                ", category=" + category +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", desc1='" + desc1 + '\'' +
                ", desc2='" + desc2 + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
