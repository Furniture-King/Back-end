package com.FurnitureKing.Project.controllers;

import com.FurnitureKing.Project.filters.ProductFilter;
import com.FurnitureKing.Project.models.Product;
import com.FurnitureKing.Project.utils.CurrentDateTime;
import com.FurnitureKing.Project.repositories.ProductRepository;
import com.FurnitureKing.Project.utils.DataFormat;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /* Get all products */
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> productList = productRepository.findAll();
        return ResponseEntity.ok(productList);
    }

    /* Search 1 product */
    @GetMapping("/products/id/{productId}")
    public ResponseEntity<Optional<Product>> getProduct(@PathVariable final ObjectId productId) {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()){
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    /* Get all products from 1 category*/
    @GetMapping("/products/category/{categoryName}")
    public List<Product> getCategoryProducts(@PathVariable final String categoryName) {
        String name = DataFormat.FormatString(categoryName);
        List<Product> product = productRepository.findProductsByCategory(name);
        if(product.isEmpty()){
            return (List<Product>) ResponseEntity.notFound().build();
        }else{
            return (List<Product>) ResponseEntity.ok(product);
        }
    }

    /* Get all products from filters */
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/products/filter")
    public List<Product> getCategoryProducts(@RequestBody final ProductFilter productfilter) {
        System.out.println("C'est bon");
        List<Product> productList = productRepository.findAll();
        if(!productfilter.getCategoryName().isEmpty()){
            productList = productList.stream().filter(product -> Objects.equals(product.getCategoryName(), productfilter.getCategoryName())).collect(Collectors.<Product>toList());
        }
        if(!productfilter.getColor().isEmpty()){
            productList = productList.stream().filter(product -> Objects.equals(product.getColor(), productfilter.getColor())).collect(Collectors.<Product>toList());
        }
            return (List<Product>) ResponseEntity.ok(productList);
    }

    /* Create product */
    @PostMapping(value = "/products/post")
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        product.setCreatedAt(CurrentDateTime.getCurrentDateTime());
        if (product.getCategoryName().isEmpty()) {
            return ResponseEntity.ok().body("missing product category");
        }
        else{
            product.setCategoryName(DataFormat.FormatString(product.getCategoryName()));
            product.setColor(DataFormat.FormatString(product.getColor()));
            productRepository.insert(product);
            return ResponseEntity.ok().body("The product is created");
        }
    }

    /* Delete 1 product */
    @DeleteMapping("/products/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable final ObjectId productId) {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()){
            productRepository.deleteById(productId);
            return ResponseEntity.ok().body("Product deleted");
        }
        return ResponseEntity.notFound().build();
    }

    /* Update 1 product */
    @PutMapping("/products/put/{productId}")
    public ResponseEntity<Optional<Product>> updateProduct(@PathVariable final ObjectId productId, @RequestBody Product productUpdate) {
        Optional<Product> product = productRepository.findById(productId);
        product.ifPresent(p -> {
            p.setName(productUpdate.getName());
            p.setColor(productUpdate.getColor());
            p.setSrcImg(productUpdate.getSrcImg());
            p.setStock(productUpdate.getStock());
            p.setStars(productUpdate.getStars());
            p.setWidth(productUpdate.getWidth());
            p.setLength(productUpdate.getLength());
            p.setPrice(productUpdate.getPrice());
            p.setDescription(productUpdate.getDescription());
            p.setDesc1(productUpdate.getDesc1());
            p.setDesc2(productUpdate.getDesc2());
            p.setUpdatedAt(CurrentDateTime.getCurrentDateTime());
            productRepository.save(p);
        });
        return ResponseEntity.ok(product);
    }
}
