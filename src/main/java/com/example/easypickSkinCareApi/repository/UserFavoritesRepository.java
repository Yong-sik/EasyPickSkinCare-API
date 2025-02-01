package com.example.easypickSkinCareApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.easypickSkinCareApi.dto.ProductsDto;
import com.example.easypickSkinCareApi.model.UserFavorites;
import com.example.easypickSkinCareApi.model.UserFavoritesId;

public interface UserFavoritesRepository extends JpaRepository<UserFavorites, UserFavoritesId>{
	
	// 사용자 즐겨찾기 목록에 있는 모든 상품을 조회
	@Query("SELECT new com.example.easypickSkinCareApi.dto.ProductsDto(p.productName, p.brand) " +
	           "FROM UserFavorites uf " +
	           "JOIN uf.products p " +
	           "WHERE uf.userId = :userId")
    List<ProductsDto> findUserFavoriteProductsfindByUserId(@Param("userId") String userId);
	
    // 유저가 선택한 제품이 이미 즐겨찾기에 등록된 상태인지 확인
    boolean existsByUserIdAndProductId(String userId, int productId);
}
