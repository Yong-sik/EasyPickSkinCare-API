package com.example.easypickSkinCareApi.dto;

public class UserFavoritesDto {
	
    private ProductsDto productsDto;

    public UserFavoritesDto(ProductsDto productsDto) {
        this.productsDto = productsDto;
    }
}
