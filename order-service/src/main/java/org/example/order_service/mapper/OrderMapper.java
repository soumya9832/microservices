package org.example.order_service.mapper;

import org.example.order_service.dto.OrderDto;
import org.example.order_service.dto.OrderLineItemsDto;
import org.example.order_service.entity.Order;
import org.example.order_service.entity.OrderLineItems;

import java.util.UUID;

public class OrderMapper {
    public static OrderLineItems mapToOrderLineItems(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
