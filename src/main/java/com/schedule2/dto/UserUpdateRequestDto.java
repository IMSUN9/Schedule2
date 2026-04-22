package com.schedule2.dto;

// 유저 수정 요청에서 클라이언트가 보낸 데이터를 담는 DTO 클래스
public class UserUpdateRequestDto {

    // 속성
    private String username;
    private String email;

    // 생성자
    public UserUpdateRequestDto() {}

    // 기능
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
