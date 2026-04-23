package com.schedule2.dto;

// 일정 생성 요청에서 클라이언트가 보낸 데이터를 담는 DTO 클래스
public class ScheduleCreateRequestDto {

    // 속성
    private String title;
    private String contents;

    // 생성자
    public ScheduleCreateRequestDto() {}

    // 기능
    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
