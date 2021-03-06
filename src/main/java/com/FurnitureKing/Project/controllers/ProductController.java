package com.FurnitureKing.Project.controllers;

import com.FurnitureKing.Project.filters.ProductFilter;
import com.FurnitureKing.Project.models.Product;
import com.FurnitureKing.Project.payload.response.MessageResponse;
import com.FurnitureKing.Project.utils.CurrentDateTime;
import com.FurnitureKing.Project.repositories.ProductRepository;
import com.FurnitureKing.Project.utils.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    /* Get products by popularity */
    @GetMapping("/products/popular")
    public ResponseEntity<List<Product>> getPopularProducts() {
        List<Product> productList = (productRepository.findAll(Sort.by(Sort.Direction.DESC, "stars")));
        return ResponseEntity.ok(productList);
    }

    /* Search 1 product */
    @GetMapping("/products/id/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable final String productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (!product.isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: product doesn't exist!"));
        } else {
            return ResponseEntity.ok(product);
        }
    }

    /* Get all products by 1 category*/
    @GetMapping("/products/category/{categoryName}")
    public ResponseEntity<?> getCategoryProducts(@PathVariable final String categoryName) {
        String name = DataFormat.FormatString(categoryName);
        List<Product> listProduct = productRepository.findProductsByCategory(name);
        if (listProduct.isEmpty()) {
            return  ResponseEntity.notFound().build();
        } else {
            return  ResponseEntity.ok(listProduct);
        }
    }

    @GetMapping("/products/category")
    public ResponseEntity<?> getCategory(){
        List<String> listCategory = productRepository.getDistinctByCategoryName();
        if(!listCategory.isEmpty()){
            return ResponseEntity.ok(listCategory);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /* Get all products from filters */
    @GetMapping("/products/filter")
    public ResponseEntity<List<Product>> getCategoryProducts(@RequestBody final ProductFilter productfilter) {

        List<Product> productList = productRepository.findAll();

        if (productfilter.getCategoryName() != null) {
            productList = productList.stream().filter(product -> Objects.equals(product.getCategoryName(), productfilter.getCategoryName())).collect(Collectors.<Product>toList());
        }
        if (productfilter.getColor() != null) {
            productList = productList.stream().filter(product -> Objects.equals(product.getColor(), productfilter.getColor())).collect(Collectors.<Product>toList());
        }
        if (productfilter.getfirstPrice() != null && productfilter.getsecondPrice() != null) {
            productList = productList.stream().filter(product -> product.getPrice() > productfilter.getfirstPrice() && product.getPrice() < productfilter.getsecondPrice()).collect(Collectors.<Product>toList());
        }
        if (productfilter.getStars() != null) {
            productList = productList.stream().filter(product -> Objects.equals(product.getStars(), productfilter.getStars())).collect(Collectors.<Product>toList());
        }
        if (productfilter.getStock() != null) {
            productList = productList.stream().filter(product -> Objects.equals(product.getStock(), productfilter.getStock())).collect(Collectors.<Product>toList());
        }
        return ResponseEntity.ok(productList);
    }

    /* Create product */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/products/post")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        product.setCreatedAt(CurrentDateTime.getCurrentDateTime());
        if (product.getCategoryName().isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: category name doesn't exist!"));

        } else {
            product.setCategoryName(DataFormat.FormatString(product.getCategoryName()));
            product.setColor(DataFormat.FormatString(product.getColor()));
            productRepository.insert(product);
            return ResponseEntity.ok().body("The product is created");
        }
    }

    /* Delete 1 product */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/products/delete/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable final String productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            productRepository.deleteById(productId);
            return ResponseEntity.ok().body("Product deleted");
        }
        return ResponseEntity.notFound().build();
    }

    /* Update 1 product */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/products/put/{productId}")
    public ResponseEntity<Optional<Product>> updateProduct(@PathVariable final String productId, @RequestBody Product productUpdate) {
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
