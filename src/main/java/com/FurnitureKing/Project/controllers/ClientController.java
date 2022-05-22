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

    @PutMapping("/clients/put/{clientId}")
    public Optional<Client> updateProduct(@PathVariable final ObjectId clientId, @RequestBody Client clientUpdate) {
        Optional<Client> client = clientRepository.findById(clientId);
        client.ifPresent(c -> {
            c.setRoles(clientUpdate.getRoles());
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
