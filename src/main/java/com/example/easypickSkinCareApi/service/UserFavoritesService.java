package com.example.easypickSkinCareApi.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.easypickSkinCareApi.dto.ProductsDto;
import com.example.easypickSkinCareApi.dto.UserFavoritesRequestDto;
import com.example.easypickSkinCareApi.model.Products;
import com.example.easypickSkinCareApi.model.Users;
import com.example.easypickSkinCareApi.repository.ProductsRepository;
import com.example.easypickSkinCareApi.repository.UserFavoritesRepository;
import com.example.easypickSkinCareApi.repository.UsersRepository;

@Service
public class UserFavoritesService {

    private UserFavoritesRepository userFavoritesRepository;
    private UsersRepository usersRepository;
    private ProductsRepository productsRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserFavoritesService.class);
    
    UserFavoritesService(UserFavoritesRepository userFavoritesRepository, 
    		UsersRepository usersRepository, 
    		ProductsRepository productsRepository ){
    	this.userFavoritesRepository = userFavoritesRepository;
    	this.usersRepository = usersRepository;
    	this.productsRepository = productsRepository;
    }

    // 사용자 즐겨찾기 목록에 있는 모든 상품을 조회
    @Query("SELECT p FROM UserFavorites uf JOIN uf.products p WHERE uf.userFavoritesId.userId = :userId")
    public List<ProductsDto> getUserFavoriteProducts(@Param("userId") String userId) {
        List<ProductsDto> favoriteProducts = userFavoritesRepository.findByUserId(userId);
        return favoriteProducts;
    }
    
    // 제품을 즐겨찾기 목록에 추가
    public boolean addUserFavorites(UserFavoritesRequestDto userFavoritesRequestDto) {
    	String userId = userFavoritesRequestDto.getUserId();
        Integer productId = userFavoritesRequestDto.getProductId();
    	
    	boolean isUserExists = usersRepository.existsById(userId);
    	boolean isProductExists = productsRepository.existsById(productId);

        if (!isUserExists || !isProductExists) {
        	logger.warn("Failed to add userFavorites: userIdExists={}, productIdExists={}", 
                    isUserExists, isProductExists);
            return false;
        }
        
        // 이미 즐겨찾기 목록에 추가된 제품인지 확인
        boolean isAlreadyFavorited = userFavoritesRepository.existsByUserIdAndProductId(userId, productId);
        if (isAlreadyFavorited) {
        	logger.warn("Failed to add userFavorites: isAlreadyFavorited={}", 
                    isAlreadyFavorited);
            return false;
        }
        
        userFavoritesRepository.save(userFavoritesRequestDto.toEntity());
        
        return true;
    }
}