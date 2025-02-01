package com.example.easypickSkinCareApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.easypickSkinCareApi.dto.ProductsDto;
import com.example.easypickSkinCareApi.model.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {

    // 이름을 기준으로 검색, pg_bigm 인덱스를 활용한 full-text search
    List<ProductsDto> findByProductNameContaining(String productName);
}