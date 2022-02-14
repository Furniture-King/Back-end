package com.FurnitureKing.Project.controllers;

import com.FurnitureKing.Project.models.Product;
import com.FurnitureKing.Project.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    /* Create product */
    @PostMapping(value = "/products")
    public List<Product> addProducts(@RequestBody Product product){
        productRepository.insert(product);
        return getProducts();
    }

    /* Get all products */
    @GetMapping("/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    /* Search 1 product */
    @GetMapping("/products/{productId}")
    public Optional<Product> getProduct(@PathVariable final String productId) {
        return productRepository.findById(productId);
    }

    /* Delete 1 product */
    @DeleteMapping("/products/{productId}")
    public List<Product> deleteProduct(@PathVariable final String productId) {
        productRepository.deleteById(productId);
        return getProducts();
    }

    @PutMapping("/products/{productId}")
    public List<Product> updateProduct(@PathVariable final String productId, @RequestBody Product productmaj) {
        Optional<Product> product = productRepository.findById(productId);
        product.ifPresent(p -> {
            p.setName(productmaj.getName());
            p.setColor(productmaj.getColor());
            p.setStock(productmaj.getStock());
            p.setStar(productmaj.getStar());
            p.setWidth(productmaj.getWidth());
            p.setLength(productmaj.getLength());
            productRepository.save(p);
        });
        return getProducts();
    }
}
