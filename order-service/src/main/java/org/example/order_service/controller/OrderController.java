package org.example.order_service.controller;

import lombok.AllArgsConstructor;
import org.example.order_service.dto.OrderDto;
import org.example.order_service.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/order")
public class OrderController {
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void placeOrder(@RequestBody OrderDto orderDto){
        orderService.placeOrder(orderDto);
    }

}
