package org.example.order_service.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryDto {
    private String skuCode;
    private Integer quantity;
}
