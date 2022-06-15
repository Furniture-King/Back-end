package com.FurnitureKing.Project.controllers;

import com.FurnitureKing.Project.models.BankCard;
import com.FurnitureKing.Project.models.Client;
import com.FurnitureKing.Project.models.Comment;
import com.FurnitureKing.Project.repositories.BankCardRepository;
import com.FurnitureKing.Project.utils.CurrentDateTime;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BankCardController {

    private final BankCardRepository bankCardRepository;

    public BankCardController(BankCardRepository bankCardRepository) {
        this.bankCardRepository = bankCardRepository;
    }

    /* Get all bankCards */
    @GetMapping("/bankCard")
    public ResponseEntity<List<BankCard>> getBankCards() {
        List<BankCard> bankCardList = bankCardRepository.findAll();
        return ResponseEntity.ok(bankCardList);
    }

    /* Search 1 bankCard by Id */
    @GetMapping("/bankCard/id/{bankCardId}")
    public ResponseEntity<Optional<BankCard>> getBankCard(@PathVariable final ObjectId bankCardId) {
        Optional<BankCard> bankCard = bankCardRepository.findById(bankCardId);
        if(bankCard.isPresent()){
            return ResponseEntity.ok(bankCard);
        }
        return ResponseEntity.notFound().build();
    }

    /* Search 1 bankCard by client */
    @GetMapping("/bankCard/{clientId}")
    public ResponseEntity<Optional<BankCard>> getBankCardByClientId(@PathVariable final ObjectId clientId) {
        Optional<BankCard> bankCard = bankCardRepository.getBankCardByClientAndId(clientId);
        if(bankCard.isPresent()){
            return ResponseEntity.ok(bankCard);
        }
        return ResponseEntity.notFound().build();
    }

    /* Create bankCard */
    @PostMapping(value = "/bankCard/post")
    public ResponseEntity<String> addBankCard(@RequestBody BankCard bankCard){
        bankCard.setCreatedAt(CurrentDateTime.getCurrentDateTime());
        bankCardRepository.insert(bankCard);
        return ResponseEntity.ok().body("The bankCard is created");
    }

    /* Delete 1 bankCard */
    @DeleteMapping("/bankCard/delete/{bankCardId}")
    public ResponseEntity<String> deleteClient(@PathVariable final ObjectId bankCardId) {
        Optional<BankCard> bankCard = bankCardRepository.findById(bankCardId);
        if(bankCard.isPresent()){
            bankCardRepository.deleteById(bankCardId);
            return ResponseEntity.ok().body("BankCard deleted");
        }
        return ResponseEntity.notFound().build();
    }


    /* Update 1 bankCard */
    @PutMapping("/bankCard/put/{bankCardId}")
    public ResponseEntity<Optional<BankCard>> updateProduct(@PathVariable final ObjectId bankCardId, @RequestBody BankCard bankCardUpdate) {
        Optional<BankCard> bankCard = bankCardRepository.findById(bankCardId);
        bankCard.ifPresent(bc -> {
            bc.setNumber(bankCardUpdate.getNumber());
            bc.setExpirationDate(bankCardUpdate.getExpirationDate());
            bc.setCvc(bankCardUpdate.getCvc());
            bc.setUpdatedAt(CurrentDateTime.getCurrentDateTime());
            bankCardRepository.save(bc);
        });
        return ResponseEntity.ok(bankCard);
    }

}
