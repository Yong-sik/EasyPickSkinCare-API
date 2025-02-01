package com.example.easypickSkinCareApi.dto;

import com.example.easypickSkinCareApi.model.UserFavorites;

public class UserFavoritesRequestDto {
	private String userId;
    private int productId;

    public UserFavoritesRequestDto(String userId, int productId){
    	this.userId = userId;
    	this.productId = productId;
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
    
	public UserFavorites toEntity() {
        UserFavorites userFavorites = new UserFavorites();
        userFavorites.setUserId(this.userId);
        userFavorites.setProductId(this.productId);
        return userFavorites;
    }
    
}
