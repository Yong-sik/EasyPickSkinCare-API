package com.example.easypickSkinCareApi.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Users {
	
	@Id
    @Column(name = "userId")
    private String userId;

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false)
    private String userPassword;

    @Column(name = "deactivationDate")
    private Timestamp deactivationDate;

    @Column(name = "createdDate", nullable = false, updatable = false)
    private Timestamp createdDate;

    // 생성자 (필요한 필드만)
    public Users(String userId, boolean isActive, String userPassword, Timestamp deactivationDate, Timestamp createdDate) {
        this.userId = userId;
        this.isActive = isActive;
        this.userPassword = userPassword;
        this.deactivationDate = deactivationDate;
        this.createdDate = createdDate;
    }
}
