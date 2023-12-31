package com.javatechie.controller;

import com.javatechie.dto.OrderResponseDTO;
import com.javatechie.exception.OrderNotFoundException;
import com.javatechie.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService service;

    @GetMapping
    public String greetingMessage() {
        return service.greeting();
    }

    @GetMapping("/orders/status/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable String orderId) throws OrderNotFoundException {
        OrderResponseDTO order = service.getOrder(orderId);
        System.out.println(order.getOrderId()+" "+order.getPrice());
        return ResponseEntity.ok(order);
    }
}
