package com.example.easypickSkinCareApi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<ProductsDto> getUserFavoriteProducts(@PathVariable("userId") String userId) {
        return userFavoritesService.getUserFavoriteProducts(userId);
    }
    
    @PostMapping
    public ResponseEntity<String> addUserFavorites(@RequestBody UserFavoritesRequestDto userFavoritesRequestDto) {
        // favoriteRequestDTO는 요청 본문에 담긴 userId와 productId를 가진 객체입니다.
    	boolean isAdded = userFavoritesService.addUserFavorites(userFavoritesRequestDto);
    	if (isAdded) {
    		return ResponseEntity.ok("즐겨찾기 등록 성공");
    	}
    	else {
    		return ResponseEntity.status(400).body("즐겨찾기 등록 실패");
    	}
    }
}