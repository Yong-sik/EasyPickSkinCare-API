package com.example.easypickSkinCareApi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.easypickSkinCareApi.dto.ProductsDto;
import com.example.easypickSkinCareApi.service.ProductsService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductsService productService;

    public ProductController(ProductsService productService) {
        this.productService = productService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductsDto>> searchProductsByName(@RequestParam String name) {
        List<ProductsDto> products = productService.searchProductsByName(name);
        return ResponseEntity.ok(products);
    }
    
}
