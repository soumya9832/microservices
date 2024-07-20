package org.example.order_service.service.impl;

import lombok.AllArgsConstructor;
import org.example.order_service.dto.InventoryDto;
import org.example.order_service.dto.OrderDto;
import org.example.order_service.entity.Order;
import org.example.order_service.entity.OrderLineItems;
import org.example.order_service.mapper.OrderMapper;
import org.example.order_service.repository.OrderRepository;
import org.example.order_service.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private WebClient.Builder webClientBuilder;

    @Override
    public void placeOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderDto.getOrderLineItemsDtoList().stream()
                .map((OrderMapper::mapToOrderLineItems))
                .toList();
        order.setOrderLineItemsList(orderLineItems);

        //Call Inventory Service, and place order if product is in stock
        List<String> skuCodes = order.getOrderLineItemsList().stream()
                        .map(OrderLineItems::getSkuCode)
                                .toList();

        InventoryDto[] inventoryDtoArray = webClientBuilder.build().get()
                        .uri("http://localhost:8080/api/inventory", uriBuilder ->
                                uriBuilder.queryParam("skuCodes",skuCodes).build())
                                .retrieve()
                                        .bodyToMono(InventoryDto[].class)
                                                .block();

        // Map the inventory response to a map for easier lookup
        Map<String, Integer> inventoryStockMap = Arrays.stream(inventoryDtoArray)
                .collect(Collectors.toMap(InventoryDto::getSkuCode, InventoryDto::getQuantity));

        // Check if all products are in stock with the required quantities
        boolean allProductsInStock = orderLineItems.stream()
                .allMatch(orderLineItem -> inventoryStockMap.getOrDefault(orderLineItem.getSkuCode(), 0) >= orderLineItem.getQuantity());


        if(allProductsInStock){
            orderRepository.save(order);


            Map<String,Integer> map = orderLineItems.stream().collect(Collectors.toMap(OrderLineItems::getSkuCode,OrderLineItems::getQuantity));
            webClientBuilder.build().post()
                    .uri("http://localhost:8080/api/inventory")
                    .body(BodyInserters.fromValue(map))
                    .retrieve()
                    .bodyToMono(Void.class)
                    .doOnError(error->{
                        System.out.println("Error Occurs during inventory update");
                    })
                    .subscribe();



        }
        else
        {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }

    }
}
