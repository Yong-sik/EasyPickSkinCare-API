package com.example.easypickSkinCareApi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductsDto {
	private Integer productId;
	@JsonProperty("productName")
    private String productName;
	@JsonProperty("brand")
    private String brand;

    public ProductsDto(Integer productId, String productName, String brand) {
    	this.productId = productId;
        this.productName = productName;
        this.brand = brand;
    }

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
    
}
