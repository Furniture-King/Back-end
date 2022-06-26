package com.FurnitureKing.Project.controller;

import com.FurnitureKing.Project.controllers.ClientController;
import com.FurnitureKing.Project.models.Client;
import com.FurnitureKing.Project.models.Product;
import com.FurnitureKing.Project.repositories.ClientRepository;
import com.FurnitureKing.Project.security.jwt.AuthEntryPointJwt;
import com.FurnitureKing.Project.security.jwt.JwtUtils;
import com.FurnitureKing.Project.security.services.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
public class clientTests {


    @MockBean
    private ClientRepository clientRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private AuthEntryPointJwt authEntryPointJwt;

    @MockBean
    private JwtUtils jwtUtils;

    @BeforeEach
    void newClients() {
        pushNewClients("test1@test.com", "password", "Dupont", "Marie", "28 rue du Caire", "75400", "Paris", "0123456789",  3);
        pushNewClients("test2@test.com", "password", "Rodrigue", "Jerome", "24 rue du Caire", "75400", "Paris", "0123456789",  5);
        pushNewClients("test3@test.com", "password", "Dame", "Clement", "22 rue du Caire", "75400", "Paris", "0123456789",  8);
        pushNewClients("test4@test.com", "password", "Arbre", "Pierre", "21 rue du Caire", "75400", "Paris", "0123456789",  15);
    }

    public void pushNewClients(String email, String password, String lastName, String firstName, String address, String postalCode, String city, String phone, Integer nbConnection) {
        Client client = new Client();
        client.setEmail(email);
        client.setPasswordHash(password);
        client.setLastName(lastName);
        client.setFirstName(firstName);
        client.setAddress(address);
        client.setPostalCode(postalCode);
        client.setCity(city);
        client.setPhone(phone);
        client.setNbConnection(nbConnection);
        clientRepository.save(client);
    }

    @Test
    void shouldReturnListOfClients() throws Exception {
        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void shouldReturnClient() throws Exception {
        mockMvc.perform(get("/clients/id/1")).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void shouldDeleteProduct() throws Exception {
        mockMvc.perform(delete("/clients/delete/2")).andExpect(status().isOk())
                .andDo(print());
    }
}
