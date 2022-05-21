package com.FurnitureKing.Project.controllers;

import com.FurnitureKing.Project.models.Product;
import com.FurnitureKing.Project.utils.CurrentDateTime;
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
    @GetMapping("/products/{categoryName}")
    public List<Product> getCategoryProducts(@PathVariable final String categoryName) {
        return productRepository.findProductByCategory(categoryName);
    }

    /* Create product */
    @PostMapping(value = "/products/post")
    public List<Product> addProduct(@RequestBody Product product){
        product.setCreatedAt(CurrentDateTime.getCurrentDateTime());
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
    public Optional<Product> updateProduct(@PathVariable final ObjectId productId, @RequestBody Product productmaj) {
        Optional<Product> product = productRepository.findById(productId);
        product.ifPresent(p -> {
            p.setName(productmaj.getName());
            p.setColor(productmaj.getColor());
            p.setSrcImg(productmaj.getSrcImg());
            p.setStock(productmaj.getStock());
            p.setStars(productmaj.getStars());
            p.setWidth(productmaj.getWidth());
            p.setLength(productmaj.getLength());
            p.setPrice(productmaj.getPrice());
            p.setDescription(productmaj.getDescription());
            p.setDesc1(productmaj.getDesc1());
            p.setDesc2(productmaj.getDesc2());
            p.setUpdatedAt(CurrentDateTime.getCurrentDateTime());
            productRepository.save(p);
        });
        return getProduct(productId);
    }
}
