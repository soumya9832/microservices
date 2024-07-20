package org.example.order_service.service;

import org.example.order_service.dto.OrderDto;

public interface OrderService {
    void placeOrder(OrderDto orderDto);
}
