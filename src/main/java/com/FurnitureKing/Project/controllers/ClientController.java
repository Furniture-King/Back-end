package com.FurnitureKing.Project.controllers;

import com.FurnitureKing.Project.models.Client;
import com.FurnitureKing.Project.repositories.ClientRepository;
import com.FurnitureKing.Project.utils.CurrentDateTime;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Client>> getClients() {
        List<Client> clientList = clientRepository.findAll();
        return (ResponseEntity<List<Client>>) ResponseEntity.ok(clientList);
    }


    /* Search 1 client by Id */
    @GetMapping("/clients/id/{clientId}")
    public ResponseEntity<Optional<Client>> getClient(@PathVariable final ObjectId clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        if(client.isPresent()){
            return ResponseEntity.ok(client);
        }
        return ResponseEntity.notFound().build();
    }

    /* Search 1 client by email */
    @GetMapping("/clients/{email}")
    public ResponseEntity<Optional<Client>> getClientByEmail(@PathVariable final String email) {
        Optional<Client> client = clientRepository.findByEmail(email);
        if(client.isPresent()){
            return ResponseEntity.ok(client);
        }
        return ResponseEntity.notFound().build();
    }

    /* Delete 1 client */
    @DeleteMapping("/clients/delete/{clientId}")
    public ResponseEntity<String> deleteClient(@PathVariable final ObjectId clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        if(client.isPresent()){
            clientRepository.deleteById(clientId);
            return ResponseEntity.ok().body("User deleted");
        }
        return ResponseEntity.notFound().build();
    }

    /* Update 1 client */
    @PutMapping("/clients/put/{clientId}")
    public ResponseEntity<Optional<Client>> updateClient(@PathVariable final ObjectId clientId, @RequestBody Client clientUpdate) {
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
        return ResponseEntity.ok(client);
    }

}
