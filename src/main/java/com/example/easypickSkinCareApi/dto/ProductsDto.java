package com.example.easypickSkinCareApi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductsDto {
	@JsonProperty("productName")
    private String productName;
	@JsonProperty("brand")
    private String brand;

    public ProductsDto(String productName, String brand) {
        this.productName = productName;
        this.brand = brand;
    }
}
