package com.FurnitureKing.Project.controller;

import com.FurnitureKing.Project.controllers.ProductController;
import com.FurnitureKing.Project.models.Product;
import com.FurnitureKing.Project.repositories.ProductRepository;
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

@WebMvcTest(ProductController.class)
public class productTests {

    @MockBean
    private ProductRepository productRepository;

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
    void newProducts() {
        pushNewProducts( "Chaise", "Akima", "Bleu",5,2.5f,150f,164f,18.00,"description longue","description courte","description très courte");
        pushNewProducts( "Bureau", "Akimo", "Bleu",5,1.5f,150f,164f,18.00,"description longue","description courte","description très courte");
        pushNewProducts( "Chaise", "Akimor", "Rouge",5,2.5f,150f,164f,18.00,"description longue","description courte","description très courte");
        pushNewProducts( "Lampe", "Akimi", "Noir",5,1.5f,150f,164f,18.00,"description longue","description courte","description très courte");
    }

    public void pushNewProducts(String categoryName, String name, String color, Integer stock, Float stars, Float width, Float length, Double price, String description, String desc1, String desc2) {
        Product product = new Product();
        product.setCategoryName(categoryName);
        product.setName(name);
        product.setColor(color);
        product.setStock(stock);
        product.setStars(stars);
        product.setWidth(width);
        product.setLength(length);
        product.setPrice(price);
        product.setDescription(description);
        product.setDescription(desc1);
        product.setDescription(desc2);
        productRepository.save(product);
    }

    @Test
    void shouldReturnListOfProducts() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void shouldCreateProduct() throws Exception {
        Product product = new Product("99", "Chaise", "Akimo", "Bleu",5,4.12345f,150f,164f,18.00,"description longue","description courte","description très courte");
        mockMvc.perform(post("/products/post").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    void shouldReturnProduct() throws Exception {
        mockMvc.perform(get("/products/id/1")).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void shouldDeleteProduct() throws Exception {
        mockMvc.perform(delete("/products/delete/2")).andExpect(status().isOk())
                .andDo(print());
    }



}
