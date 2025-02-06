package com.example.easypickSkinCareApi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.easypickSkinCareApi.dto.ApiResponse;
import com.example.easypickSkinCareApi.dto.ProductsDto;
import com.example.easypickSkinCareApi.dto.UserFavoritesRequestDto;
import com.example.easypickSkinCareApi.service.UserFavoritesService;

@RestController
@RequestMapping("/api/user_favorites")
public class UserFavoriteController {

    private UserFavoritesService userFavoritesService;
    
    private UserFavoriteController(UserFavoritesService userFavoriteService) {
        this.userFavoritesService = userFavoriteService;
    }

    // 즐겨찾기 목록 조회 API
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<List<ProductsDto>>> getUserFavoriteProducts(@PathVariable("userId") String userId) {
    	ApiResponse<List<ProductsDto>> response = userFavoritesService.getUserFavoriteProducts(userId);
		return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<UserFavoritesRequestDto>> addUserFavorites(@RequestBody UserFavoritesRequestDto userFavoritesRequestDto) {
    	// favoriteRequestDTO는 요청 본문에 담긴 userId와 productId를 가진 객체입니다.
    	ApiResponse<UserFavoritesRequestDto> response = userFavoritesService.addUserFavorites(userFavoritesRequestDto);
		return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    
    @DeleteMapping
    public ResponseEntity<ApiResponse<UserFavoritesRequestDto>> deleteUserFavorites(@RequestBody UserFavoritesRequestDto userFavoritesRequestDto) {
        // favoriteRequestDTO는 요청 본문에 담긴 userId와 productId를 가진 객체입니다.
    	ApiResponse<UserFavoritesRequestDto> response = userFavoritesService.deleteUserFavorites(userFavoritesRequestDto);
		return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}