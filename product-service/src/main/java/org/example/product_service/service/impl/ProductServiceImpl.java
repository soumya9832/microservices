package org.example.product_service.service.impl;

import lombok.AllArgsConstructor;
import org.example.product_service.dto.ProductDto;
import org.example.product_service.entity.Product;
import org.example.product_service.mapper.ProductMapper;
import org.example.product_service.repository.ProductRepository;
import org.example.product_service.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Override
    public void createProduct(ProductDto productDto) {
        Product product = ProductMapper.mapToProduct(productDto);
        productRepository.save(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(ProductMapper::mapToProductDto)
                .collect(Collectors.toList());
    }
}
