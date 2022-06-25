package com.FurnitureKing.Project.controllers;

import com.FurnitureKing.Project.models.Client;
import com.FurnitureKing.Project.models.Product;
import com.FurnitureKing.Project.models.Transporter;
import com.FurnitureKing.Project.payload.response.MessageResponse;
import com.FurnitureKing.Project.repositories.TransporterRepository;
import com.FurnitureKing.Project.utils.CurrentDateTime;
import com.FurnitureKing.Project.utils.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TransporterController {

    private final TransporterRepository transporterRepository;

    @Autowired
    public TransporterController(TransporterRepository transporterRepository) {this.transporterRepository = transporterRepository;}

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/transporters")
    public ResponseEntity<List<Transporter>> getTransporters() {
        List<Transporter> transporterList = transporterRepository.findAll();
        return ResponseEntity.ok(transporterList);
    }

    /* Search 1 transporter by Id */
    @GetMapping("/transporters/id/{transporterId}")
    public ResponseEntity<?> getTransporter(@PathVariable final String transporterId) {
        Optional<Transporter> transporter = transporterRepository.findById(transporterId);
        if (!transporter.isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: transporter doesn't exist!"));
        } else {
            return ResponseEntity.ok(transporter);
        }
    }

    /* Delete 1 transporter */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/transporters/delete/{transporterId}")
    public ResponseEntity<?> deleteTransporter(@PathVariable final String transporterId) {

        Optional<Transporter> transporter = transporterRepository.findById(transporterId);

        if (!transporter.isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: transporter doesn't exist!"));
        } else {
            transporterRepository.deleteById(transporterId);
            return ResponseEntity.ok().body(new MessageResponse("Transporter deleted"));
        }

    }

    /* Create transporter */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/transporters/post")
    public ResponseEntity<?> addTransporter(@RequestBody Transporter transporter) {
        transporter.setCreatedAt(CurrentDateTime.getCurrentDateTime());
        transporterRepository.insert(transporter);
            return ResponseEntity.ok().body("The transporter is created");
    }

    /* Update 1 transporter */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/transporters/put/{transporterId}")
    public ResponseEntity<?> updateTransporter(@PathVariable final String transporterId, @RequestBody Transporter transporterUpdate) {
        Optional<Transporter> transporter = transporterRepository.findById(transporterId);
        if (!transporter.isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: transporter doesn't exist!"));
        } else {
            transporter.ifPresent(t -> {
                if(transporterUpdate.getDeliveryPrice() != null){
                    t.setDeliveryPrice(transporterUpdate.getDeliveryPrice());
                }
                if(transporterUpdate.getUndertaking() != null){
                    t.setUndertaking(transporterUpdate.getUndertaking());
                }
                if(transporterUpdate.getAddress() != null){
                    t.setAddress(transporterUpdate.getAddress());
                }
                if(transporterUpdate.getPostalCode() != null){
                    t.setPostalCode(transporterUpdate.getPostalCode());
                }
                if(transporterUpdate.getCity() != null){
                    t.setCity(transporterUpdate.getCity());
                }
                t.setUpdatedAt(CurrentDateTime.getCurrentDateTime());
                transporterRepository.save(t);
            });
            return ResponseEntity.ok(transporter);
        }
    }

}
