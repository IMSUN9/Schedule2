package com.schedule2.dto;

// 로그인 요청에서 클라이언트가 보낸 데이터를 다믄 DTO 클래스
public class LoginRequestDto {

    // 속성
    private String email;
    private String password;

    // 생성자
    public LoginRequestDto() {}

    // 기능
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
