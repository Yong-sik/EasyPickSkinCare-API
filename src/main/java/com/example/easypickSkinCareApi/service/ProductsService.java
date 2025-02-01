package com.example.easypickSkinCareApi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.easypickSkinCareApi.dto.ProductsDto;
import com.example.easypickSkinCareApi.repository.ProductsRepository;

@Service
public class ProductsService {

    private final ProductsRepository productRepository;

    public ProductsService(ProductsRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 제품 이름으로 검색
    public List<ProductsDto> searchProductsByName(String name) {
    	System.out.println("Searching for products with name containing: {}" + name);
        List<ProductsDto> products = productRepository.findByProductNameContaining(name);
        System.out.println("Found products: {}" + products);
        return productRepository.findByProductNameContaining(name);
    }
}