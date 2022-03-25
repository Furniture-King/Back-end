package com.FurnitureKing.Project.controllers;

import com.FurnitureKing.Project.models.Product;
import com.FurnitureKing.Project.repositories.ProductRepository;
import org.bson.types.ObjectId;
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

    /* Get all products */
    @GetMapping("/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    /* Search 1 product */
    @GetMapping("/products/id/{productId}")
    public Optional<Product> getProduct(@PathVariable final ObjectId productId) {
        return productRepository.findById(productId);
    }

    /* Get all products from 1 category*/
    @GetMapping("/products/{categoryId}")
    public List<Product> getCategoryProducts(@PathVariable final Integer categoryId) {
        return productRepository.findProductByCategory(categoryId);
    }

    /* Create product */
    @PostMapping(value = "/products/post")
    public List<Product> addProduct(@RequestBody Product product){
        productRepository.insert(product);
        return getProducts();
    }

    /* Delete 1 product */
    @DeleteMapping("/products/delete/{productId}")
    public List<Product> deleteProduct(@PathVariable final ObjectId productId) {
        productRepository.deleteById(productId);
        return getProducts();
    }

    /* Update 1 product */
    @PutMapping("/products/put/{productId}")
    public List<Product> updateProduct(@PathVariable final ObjectId productId, @RequestBody Product productmaj) {
        Optional<Product> product = productRepository.findById(productId);
        product.ifPresent(p -> {
            p.setName(productmaj.getName());
            p.setColor(productmaj.getColor());
            p.setStock(productmaj.getStock());
            p.setStar(productmaj.getStar());
            p.setWidth(productmaj.getWidth());
            p.setLength(productmaj.getLength());
            p.setCategory(productmaj.getCategory());
            p.setPrice(productmaj.getPrice());
            p.setDescription(productmaj.getDescription());
            p.setDesc1(productmaj.getDesc1());
            p.setDesc2(productmaj.getDesc2());
            p.setCreatedAt(productmaj.getCreatedAt());
            p.setUpdatedAt(productmaj.getUpdatedAt());

            productRepository.save(p);
        });
        return getProducts();
    }
}
