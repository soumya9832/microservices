package org.example.product_service.service;

import org.example.product_service.dto.ProductDto;

import java.util.List;


public interface ProductService {
    void createProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();
}
