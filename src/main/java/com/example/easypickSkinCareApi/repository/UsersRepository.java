package com.example.easypickSkinCareApi.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.easypickSkinCareApi.model.Users;

public interface UsersRepository extends JpaRepository<Users, String>{	
	// 사용자 정의 쿼리 작성
//    List<ProductsDto> findFavoritesByUserId(String userId);
}
