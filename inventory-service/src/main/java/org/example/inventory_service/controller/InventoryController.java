package org.example.inventory_service.controller;

import lombok.AllArgsConstructor;
import org.example.inventory_service.dto.InventoryDto;
import org.example.inventory_service.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/inventory")
public class InventoryController {
    private  InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryDto> isInStock(@RequestParam List<String> skuCodes) {
        return inventoryService.isInStock(skuCodes);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateInventory(@RequestBody Map<String,Integer> orderLineItemsMap){
        inventoryService.updateInventory(orderLineItemsMap);
    }
}
