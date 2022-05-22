package com.FurnitureKing.Project.controllers;

import com.FurnitureKing.Project.models.Client;
import com.FurnitureKing.Project.repositories.ClientRepository;
import com.FurnitureKing.Project.utils.CurrentDateTime;
import com.FurnitureKing.Project.utils.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthController {

    private final ClientRepository clientRepository;

    @Autowired
    public AuthController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    /* Sign-up */
    @PostMapping(value = "/clients/sign-up")
    public Client addClient(@RequestBody Client client){
        client.setCreatedAt(CurrentDateTime.getCurrentDateTime());
        client.setNbConnection(1);
        String[] hshPsw = Password.Hash(client.getPasswordHash());
        client.setPasswordHash(hshPsw[0]);
        client.setPasswordSalt(hshPsw[1]);
        clientRepository.insert(client);
        return client;
    }

    /* Sign-in check */
    @PostMapping(value = "/clients/sign-in")
    public Object checkClient(@RequestBody Client data){
        System.out.println("test");
        Optional<Client> client = clientRepository.findByEmail(data.getEmail());
        final Boolean[] bool = {false};
        client.ifPresent(c -> {
            c.setNbConnection(c.getNbConnection() + 1);
            bool[0] = Password.Check(data.getPasswordHash(), c.getPasswordSalt());
        });
        System.out.println(bool[0]);
        if(bool[0]){
            return client;
        }else{
            return "Mauvais mail ou mot de passe";
        }
    }

}
