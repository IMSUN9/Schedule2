package com.schedule2.dto;

import com.schedule2.entity.Schedule;

import java.time.LocalDateTime;

// 일정 응답 데이터를 클라이언트에게 돌려줄 때 사용하는 DTO 클래스
public class ScheduleResponseDto {

    // 속성
    private Long userId;
    private Long id;
    private String username;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 생성자
    public ScheduleResponseDto() {}

    public ScheduleResponseDto(Long id,Long userId, String username, String title, String contents, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.title = title;
        this.contents = contents;
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

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Long getUserId() {
        return userId;
    }

}
