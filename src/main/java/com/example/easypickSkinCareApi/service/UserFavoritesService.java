package com.example.easypickSkinCareApi.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.easypickSkinCareApi.dto.ApiResponse;
import com.example.easypickSkinCareApi.dto.ProductsDto;
import com.example.easypickSkinCareApi.dto.UserFavoritesRequestDto;
import com.example.easypickSkinCareApi.model.Products;
import com.example.easypickSkinCareApi.model.UserFavoritesId;
import com.example.easypickSkinCareApi.model.Users;
import com.example.easypickSkinCareApi.repository.ProductsRepository;
import com.example.easypickSkinCareApi.repository.UserFavoritesRepository;
import com.example.easypickSkinCareApi.repository.UsersRepository;

@Service
public class UserFavoritesService {

    private UserFavoritesRepository userFavoritesRepository;
    private UsersRepository usersRepository;
    private ProductsRepository productsRepository;
    
    UserFavoritesService(UserFavoritesRepository userFavoritesRepository, 
    		UsersRepository usersRepository, 
    		ProductsRepository productsRepository ){
    	this.userFavoritesRepository = userFavoritesRepository;
    	this.usersRepository = usersRepository;
    	this.productsRepository = productsRepository;
    }

    // 사용자 즐겨찾기 목록에 있는 모든 상품을 조회
    public ApiResponse<List<ProductsDto>> getUserFavoriteProducts(@Param("userId") String userId) {
    	
    	boolean isUserExists = usersRepository.existsById(userId);
    	
    	// users 테이블에 요청을 보낸 유저가 존재하는지 확인 -> 존재하지 않으면 실패 처리
        if (!isUserExists) {
            return new ApiResponse<>(400, "유효하지 않은 사용자입니다. 재로그인 후 시도해주세요.");
        }
        
        List<ProductsDto> favoriteProducts = userFavoritesRepository.findUserFavoriteProductsfindByUserId(userId);
        
        return new ApiResponse<>(200, "즐겨찾기 목록 조회 성공", favoriteProducts);
    }
    
    // 제품을 즐겨찾기 목록에 추가
    public ApiResponse<UserFavoritesRequestDto> addUserFavorites(UserFavoritesRequestDto userFavoritesRequestDto) {
    	String userId = userFavoritesRequestDto.getUserId();
        Integer productId = userFavoritesRequestDto.getProductId();
    	
    	boolean isUserExists = usersRepository.existsById(userId);
    	boolean isProductExists = productsRepository.existsById(productId);

    	// users 테이블에 요청을 보낸 유저가 존재하는지 확인 -> 존재하지 않으면 실패 처리
        if (!isUserExists) {
            return new ApiResponse<>(400, "유효하지 않은 사용자입니다. 재로그인 후 시도해주세요.");
        }
        
        // products 테이블에 유저가 요청한 제품이 존재하는지 확인 -> 존재하지 않으면 실패 처리
        if (!isProductExists) {
            return new ApiResponse<>(400, "해당 제품은 유효하지 않은 제품입니다. 새로고침 후 시도해주세요.");
        }
        
        // 이미 즐겨찾기 목록에 추가된 제품인지 확인 -> 이미 존재하는 제품이면 실패 처리
        boolean isAlreadyFavorited = userFavoritesRepository.existsByUserIdAndProductId(userId, productId);
        if (isAlreadyFavorited) {
        	return new ApiResponse<>(400, "해당 제품은 이미 즐겨찾기에 등록된 상품입니다. 새로고침 후 시도해주세요.");
        }
        
        userFavoritesRepository.save(userFavoritesRequestDto.toEntity());
        
        return new ApiResponse<>(200, "즐겨찾기 등록에 성공했습니다.", userFavoritesRequestDto);
    }
    
    // 해당 제품을 즐겨찾기 목록에서 삭제
    public ApiResponse<UserFavoritesRequestDto> deleteUserFavorites(UserFavoritesRequestDto userFavoritesRequestDto) {
    	String userId = userFavoritesRequestDto.getUserId();
        Integer productId = userFavoritesRequestDto.getProductId();
    	
    	boolean isUserExists = usersRepository.existsById(userId);
    	boolean isProductExists = productsRepository.existsById(productId);

    	// users 테이블에 요청을 보낸 유저가 존재하는지 확인 -> 존재하지 않으면 실패 처리
        if (!isUserExists) {
            return new ApiResponse<>(400, "유효하지 않은 사용자입니다. 재로그인 후 시도해주세요.");
        }
        
        // products 테이블에 유저가 요청한 제품이 존재하는지 확인 -> 존재하지 않으면 실패 처리
        if (!isProductExists) {
            return new ApiResponse<>(400, "해당 제품은 유효하지 않은 제품입니다. 새로고침 후 시도해주세요.");
        }
        
        // 이미 즐겨찾기 목록에 추가된 제품인지 확인 -> 즐겨찾기 목록에 없는 제품이면 실패 처리 
        boolean isAlreadyFavorited = userFavoritesRepository.existsByUserIdAndProductId(userId, productId);
        if (!isAlreadyFavorited) {
            return new ApiResponse<>(400, "해당 제품은 즐겨찾기 목록에 존재하지 않습니다. 새로고침 후 시도해주세요.");
        }
        
        userFavoritesRepository.deleteById( new UserFavoritesId( userId, productId ) );
        
        return new ApiResponse<>(200, "즐겨찾기 등록 해제에 성공했습니다.", userFavoritesRequestDto);
    }
}