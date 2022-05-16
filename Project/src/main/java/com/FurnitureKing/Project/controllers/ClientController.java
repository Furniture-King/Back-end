package com.FurnitureKing.Project.controllers;

import com.FurnitureKing.Project.models.Client;
import com.FurnitureKing.Project.repositories.ClientRepository;
import com.FurnitureKing.Project.utils.Password;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /* Get all clients */
    @GetMapping("/clients")
    public List<Client> getClients() {
        return clientRepository.findAll();}

    /* Search 1 client by Id */
    @GetMapping("/clients/id/{clientId}")
    public Optional<Client> getClient(@PathVariable final ObjectId clientId) {
        return clientRepository.findById(clientId);
    }

    /* Search 1 client by email */
    @GetMapping("/clients/{email}")
    public Optional<Client> getClient(@PathVariable final String email) {
        return clientRepository.findByEmail(email);
    }

    /* Delete 1 client */
    @DeleteMapping("/clients/delete/{clientId}")
    public List<Client> deleteClient(@PathVariable final ObjectId clientId) {
        clientRepository.deleteById(clientId);
        return getClients();
    }

    /* Sign-up */
    @PostMapping(value = "/clients/sign-up")
    public Client addClient(@RequestBody Client client){
        System.out.println(client);
        String[] hshPsw = Password.Hash(client.getPasswordHash());
        client.setPasswordHash(hshPsw[0]);
        client.setPasswordSalt(hshPsw[1]);
        clientRepository.insert(client);
        return client;
    }

    /* Sign-in check */
    @GetMapping(value = "/clients/sign-in")
    public Object checkClient(@RequestBody String psw, String email){
        Optional<Client> client = clientRepository.findByEmail(email);
        final Boolean[] bool = {false};
        client.ifPresent(c -> {
            bool[0] = Password.Check(psw, c.getPasswordHash(), c.getPasswordSalt());
        });
        if(bool[0] == true){

            return client;
        }else{
            return null;
        }
    }
}
