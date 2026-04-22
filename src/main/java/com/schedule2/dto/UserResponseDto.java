package com.schedule2.dto;

import java.time.LocalDateTime;

// 유저 응답 데이터를 클라이언트에게 돌려줄 때 사용하는 DTO 클래스
public class UserResponseDto {

    // 속성
    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 생성자
    // JPA용
    public UserResponseDto() {}

    // 개발자용
    public UserResponseDto(Long id, String username, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // 기능
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
