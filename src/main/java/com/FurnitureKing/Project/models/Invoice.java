package com.FurnitureKing.Project.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

public class Invoice {
    @Id
    private ObjectId id;
    @Field
    private ObjectId user_id;
    @Field
    private Double amount_ht;
    @Field
    private Double amount_ttc;
    @Field
    private Double amount_tva;
    @Field
    private Double discount;
    @Field
    private String title;
    @Field
    private Double invoice_num;
    @Field
    private Date createdAt;
    @Field
    private Date updatedAt;

    //JE SAIS PAS QUOI FAIRE DE CA MAIS CA PEUX ETRE UTILE
}
