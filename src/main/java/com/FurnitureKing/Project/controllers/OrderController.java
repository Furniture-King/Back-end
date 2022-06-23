package com.FurnitureKing.Project.controllers;

import com.FurnitureKing.Project.models.Order;
import com.FurnitureKing.Project.repositories.OrderRepository;
import com.FurnitureKing.Project.utils.CurrentDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {this.orderRepository = orderRepository;}


    /* Get all orders */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orderList = orderRepository.findAll();
        return ResponseEntity.ok(orderList);
    }

    /* Search 1 order by Id */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/orders/id/{orderId}")
    public ResponseEntity<Optional<Order>> getOrder(@PathVariable final String orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isPresent()){
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.notFound().build();
    }

    /* Create order */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping(value = "/orders/post")
    public ResponseEntity<String> addOrder(@RequestBody Order order){
        order.setCreatedAt(CurrentDateTime.getCurrentDateTime());
        if(order.getBasketTabs().isEmpty()){
            return ResponseEntity.badRequest().body("No product in this order");
        }else{
            orderRepository.insert(order);
            return ResponseEntity.ok().body("The order is created");
        }

    }

    /* Delete 1 order */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/orders/delete/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable final String orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isPresent()){
            orderRepository.deleteById(orderId);
            return ResponseEntity.ok().body("Order deleted");
        }
        return ResponseEntity.notFound().build();
    }

    /* Update 1 order */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/orders/put/{orderId}")
    public ResponseEntity<Optional<Order>> updateProduct(@PathVariable final String orderId, @RequestBody Order orderUpdate) {
        Optional<Order> order = orderRepository.findById(orderId);
        order.ifPresent(o -> {
            o.setAddress(orderUpdate.getAddress());
            o.setCity(orderUpdate.getCity());
            o.setPostalCode(orderUpdate.getPostalCode());
            o.setPrice(orderUpdate.getPrice());
            o.setUpdatedAt(CurrentDateTime.getCurrentDateTime());
            orderRepository.save(o);
        });
        return ResponseEntity.ok(order);
    }
}
