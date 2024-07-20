package org.example.product_service.controller;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.product_service.dto.ProductDto;
import org.example.product_service.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private ProductService productService;

    //Build Add Product API
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody  ProductDto productDto){
        productService.createProduct(productDto);
    }

    //Build Get All Products API
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

}
