package com.FurnitureKing.Project.controllers;

import com.FurnitureKing.Project.models.Client;
import com.FurnitureKing.Project.payload.response.MessageResponse;
import com.FurnitureKing.Project.repositories.ClientRepository;
import com.FurnitureKing.Project.security.jwt.JwtUtils;
import com.FurnitureKing.Project.security.services.UserDetailsServiceImpl;
import com.FurnitureKing.Project.utils.CurrentDateTime;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {

    private final ClientRepository clientRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /* Get all clients */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getClients() {
        List<Client> clientList = clientRepository.findAll();
        return ResponseEntity.ok(clientList);
    }

    /* Search 1 client by Id */
    @GetMapping("/clients/id/{clientId}")
    public ResponseEntity<?> getClient(@PathVariable final String clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        if(!client.isPresent()){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: client doesn't exist!"));
        }
        return ResponseEntity.ok(client);
    }

    /* Search 1 client by email */
    @GetMapping("/clients/email/{email}")
    public ResponseEntity<?> getClientByEmail(@PathVariable final String email) {
        Optional<Client> client = clientRepository.findByEmail(email);
        if(!client.isPresent()){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: email doesn't exist!"));
        }
        return ResponseEntity.ok(client);
    }

    /* Delete 1 client */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/clients/delete/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable final String clientId ) {

        Optional<Client> client = clientRepository.findById(clientId);

        if(!client.isPresent()){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: client doesn't exist!"));
        }

        clientRepository.deleteById(clientId);
        return ResponseEntity.ok().body(new MessageResponse("User deleted"));
    }

    /* Update 1 client */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/clients/put/{clientId}")
    public ResponseEntity<?> updateClient(@PathVariable final String clientId, @RequestBody Client clientUpdate) {
        Optional<Client> client = clientRepository.findById(clientId);
        if(!client.isPresent()){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: client doesn't exist!"));
        }
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
