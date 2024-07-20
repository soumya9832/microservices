package org.example.inventory_service.service;

import org.example.inventory_service.dto.InventoryDto;

import java.util.List;
import java.util.Map;

public interface InventoryService {
    List<InventoryDto> isInStock(List<String> skuCode);
    void updateInventory(Map<String, Integer> orderLineItemsMap);

}
