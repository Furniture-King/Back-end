package com.FurnitureKing.Project.repositories;

import com.FurnitureKing.Project.models.BankCard;
import com.FurnitureKing.Project.models.Client;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankCardRepository
        extends MongoRepository<BankCard, ObjectId> {

    Optional<BankCard> getBankCardByClientAndId(ObjectId clientId);

    void deleteById(ObjectId bankCardId);
}
