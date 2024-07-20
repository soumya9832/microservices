package org.example.product_service.mapper;

import org.example.product_service.dto.ProductDto;
import org.example.product_service.entity.Product;

public class ProductMapper {
    public static ProductDto mapToProductDto(Product product){
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

    public static Product mapToProduct(ProductDto productDto){
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice()
        );
    }
}
