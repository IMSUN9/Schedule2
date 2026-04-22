package com.schedule2.dto;

// 일정 수정 요청에서 클라이언트가 보낼 데이터를 담는 DTO 클래스
public class ScheduleUpdateRequestDto {

    // 속성
    private String username;
    private String title;
    private String contents;

    // 생성자
    // 기본 생성자
    public ScheduleUpdateRequestDto() {}

    // 기능
    public String getUsername() {
        return username;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
