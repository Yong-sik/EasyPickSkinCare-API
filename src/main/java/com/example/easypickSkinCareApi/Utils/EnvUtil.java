package com.example.easypickSkinCareApi.Utils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvUtil {
	public static void initSystemProperty() {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("SERVER_IP", dotenv.get("SERVER_IP"));
        System.setProperty("DB_HOST_PORT", dotenv.get("DB_HOST_PORT"));
        System.setProperty("POSTGRES_DB", dotenv.get("POSTGRES_DB"));
        System.setProperty("POSTGRES_USER", dotenv.get("POSTGRES_USER"));
        System.setProperty("POSTGRES_PASSWORD", dotenv.get("POSTGRES_PASSWORD"));
    }
}
