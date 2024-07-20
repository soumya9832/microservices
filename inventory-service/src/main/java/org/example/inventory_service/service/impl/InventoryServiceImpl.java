package org.example.inventory_service.service.impl;

import lombok.AllArgsConstructor;
import org.example.inventory_service.dto.InventoryDto;
import org.example.inventory_service.entity.Inventory;
import org.example.inventory_service.repository.InventoryRepository;
import org.example.inventory_service.service.InventoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private InventoryRepository inventoryRepository;

    @Override
    public List<InventoryDto> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                        InventoryDto.builder()
                                .skuCode(inventory.getSkuCode())
                                .quantity(inventory.getQuantity())
                                .build())
                .toList();
    }

    @Override
    public void updateInventory(Map<String, Integer> orderLineItemsMap) {
        for(Map.Entry<String,Integer> entry : orderLineItemsMap.entrySet()){
            String skuCode=entry.getKey();
            Integer quantity = entry.getValue();

            Inventory inventory = inventoryRepository.findBySkuCode(skuCode);

            inventory.setQuantity(inventory.getQuantity()-quantity);

            inventoryRepository.save(inventory);
        }
    }


}
