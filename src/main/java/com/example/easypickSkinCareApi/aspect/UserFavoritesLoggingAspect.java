package com.example.easypickSkinCareApi.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.easypickSkinCareApi.dto.ApiResponse;
import com.example.easypickSkinCareApi.dto.UserFavoritesRequestDto;

@Aspect
@Component
public class UserFavoritesLoggingAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(UserFavoritesLoggingAspect.class);
	
    @Around("execution(* com.example.easypickSkinCareApi.service.UserFavoritesService.deleteUserFavorites(..))")
    public Object logUserFavoritesActions(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        UserFavoritesRequestDto userFavoritesRequestDto = (UserFavoritesRequestDto) args[0];
        
        String userId = userFavoritesRequestDto.getUserId();
        Integer productId = userFavoritesRequestDto.getProductId();

        try {
            logger.info("🔹 [{}] 유효성 검사 시작 - userId={}, productId={}", methodName, userId, productId);
            Object result = joinPoint.proceed(); // 실제 서비스 메소드 실행

            ApiResponse<?> response = (ApiResponse<?>) result;
            if (response.getStatusCode() == 200) {
                logger.info("✅ [{}] 즐겨찾기 삭제 성공 - userId={}, productId={}", methodName, userId, productId);
            } else {
                logger.warn("⚠️ [{}] 즐겨찾기 삭제 실패 - reason={}", methodName, response.getMessage());
            }

            return result;
        } catch (Exception e) {
            logger.error("❌ [{}] 에러 발생 - message={}, userId={}, productId={}", methodName, e.getMessage(), userId, productId);
            
            return new ApiResponse<>(500, "서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
        }
    }
    
    // 조회 메서드 로깅
    @Around("execution(* com.example.easypickSkinCareApi.service.UserFavoritesService.getUserFavoriteProducts(..))")
    public Object logGetUserFavoriteProducts(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        String userId = (String) args[0];

        try {
            logger.info("🔹 [{}] 유효성 검사 시작 - userId={}", methodName, userId);
            Object result = joinPoint.proceed(); // 실제 서비스 메소드 실행

            ApiResponse<?> response = (ApiResponse<?>) result;
            if (response.getStatusCode() == 200 && response.getData() != null ) {
                logger.info("✅ [{}] 즐겨찾기 목록 조회 성공 - userId={}", methodName, userId);
            } else {
                logger.info("⚠️ [{}] 즐겨찾기 목록 조회 실패 - userId={}", methodName, userId);
            }

            return result;
        } catch (Exception e) {
            logger.error("❌ [{}] 에러 발생 - message={}, userId={}", methodName, e.getMessage(), userId);
            return new ApiResponse<>(500, "서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
        }
    }
    
    // 등록 메서드 로깅
    @Around("execution(* com.example.easypickSkinCareApi.service.UserFavoritesService.addUserFavorites(..))")
    public Object logAddUserFavorites(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        UserFavoritesRequestDto userFavoritesRequestDto = (UserFavoritesRequestDto) args[0];
        
        String userId = userFavoritesRequestDto.getUserId();
        Integer productId = userFavoritesRequestDto.getProductId();

        try {
            logger.info("🔹 [{}] 유효성 검사 시작 - userId={}, productId={}", methodName, userId, productId);
            Object result = joinPoint.proceed(); // 실제 서비스 메소드 실행

            ApiResponse<?> response = (ApiResponse<?>) result;
            if (response.getStatusCode() == 200) {
                logger.info("✅ [{}] 즐겨찾기 등록 성공 - userId={}, productId={}", methodName, userId, productId);
            } else {
                logger.warn("⚠️ [{}] 즐겨찾기 등록 실패 - reason={}", methodName, response.getMessage());
            }

            return result;
        } catch (Exception e) {
            logger.error("❌ [{}] 에러 발생 - message={}, userId={}, productId={}", methodName, e.getMessage(), userId, productId);
            return new ApiResponse<>(500, "서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
        }
    }
}
