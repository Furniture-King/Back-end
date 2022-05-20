package com.FurnitureKing.Project.controllers;

import com.FurnitureKing.Project.models.Client;
import com.FurnitureKing.Project.repositories.ClientRepository;
import com.FurnitureKing.Project.utils.CurrentDateTime;
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
        client.setCreatedAt(CurrentDateTime.getCurrentDateTime());
        client.setNbConnection(1);
        String[] hshPsw = Password.Hash(client.getPasswordHash());
        client.setPasswordHash(hshPsw[0]);
        client.setPasswordSalt(hshPsw[1]);
        clientRepository.insert(client);
        return client;
    }

    /* Sign-in check */
    @GetMapping(value = "/clients/sign-in")
    public Object checkClient(@RequestBody Client data){
        System.out.println("Entrée dans le fonction");
        Optional<Client> client = clientRepository.findByEmail(data.getEmail());
        final Boolean[] bool = {false};
        client.ifPresent(c -> {
            System.out.println("client est bien présent");
            c.setNbConnection(c.getNbConnection() + 1);
            System.out.println(Password.Check(data.getPasswordHash(), c.getPasswordHash(), c.getPasswordSalt()));
            bool[0] = Password.Check(data.getPasswordHash(), c.getPasswordHash(), c.getPasswordSalt());
        });
        System.out.println(bool[0]);
        if(bool[0]){
            return client;
        }else{
            return "pas bon";
        }
    }

    @PutMapping("/clients/put/{clientId}")
    public Optional<Client> updateProduct(@PathVariable final ObjectId clientId, @RequestBody Client clientUpdate) {
        Optional<Client> client = clientRepository.findById(clientId);
        client.ifPresent(c -> {
            c.setStatus(clientUpdate.getStatus());
            c.setEmail(clientUpdate.getEmail());
            c.setCivility(clientUpdate.getCivility());
            c.setLastName(clientUpdate.getLastName());
            c.setFirstName(clientUpdate.getFirstName());
            c.setAddress(clientUpdate.getAddress());
            c.setPostalCode(clientUpdate.getPostalCode());
            c.setCity(clientUpdate.getCity());
            c.setPhone(clientUpdate.getPhone());
            c.setFavProduct(clientUpdate.getFavProduct());
            c.setUpdatedAt(CurrentDateTime.getCurrentDateTime());
            clientRepository.save(c);
        });
        return getClient(clientId);
    }
}
