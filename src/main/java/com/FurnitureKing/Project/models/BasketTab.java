package com.FurnitureKing.Project.models;

import org.bson.types.ObjectId;

public class BasketTab {

    private ObjectId productId;

    private String productName;

    private Integer qté;

    public String getProductName() {return productName;}
    public void setProductName(String productName) {this.productName = productName;}

    public ObjectId getProductId() {return productId;}
    public void setProductId(ObjectId productId) {this.productId = productId;}

    public Integer getQté() {return qté;}
    public void setQté(Integer qté) {this.qté = qté;}

    public BasketTab() {}
}
