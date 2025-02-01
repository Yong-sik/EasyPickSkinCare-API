package com.example.easypickSkinCareApi.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


public class UserFavoritesId implements Serializable {
	
	// 해당 UID를 지정해주지 않으면, 객체가 변경될 때마다 새로운 ID를 가진 객체를 생성함. 따라서 같은 객체라고 생각하고 개발하다가 문제가 생길 수 있음.
	private static final long serialVersionUID = 1L;
	
	
    private String userId;
	
    private Integer productId;

    public UserFavoritesId() {}

    public UserFavoritesId(String userId, int productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFavoritesId that = (UserFavoritesId) o;
        return userId.equals(that.userId) && productId.equals(that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, productId);
    }
}