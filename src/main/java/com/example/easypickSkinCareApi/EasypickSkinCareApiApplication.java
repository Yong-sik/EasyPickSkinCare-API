package com.example.easypickSkinCareApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.example.easypickSkinCareApi.Utils.EnvUtil;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EntityScan(basePackages = "com.example.easypickSkinCareApi.model") 
public class EasypickSkinCareApiApplication {

	public static void main(String[] args) {
		
		EnvUtil.initSystemProperty();
		
		SpringApplication.run(EasypickSkinCareApiApplication.class, args);
	}
}
