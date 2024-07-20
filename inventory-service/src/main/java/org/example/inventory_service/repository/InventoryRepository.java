package org.example.inventory_service.repository;

import org.example.inventory_service.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    List<Inventory> findBySkuCodeIn(List<String> skuCode);

    Inventory findBySkuCode(String skuCode);
}
