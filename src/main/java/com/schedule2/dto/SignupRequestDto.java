package com.schedule2.dto;

// 회원가입 요청에서 클라이언트가 보낸 데이터를 담는 DTO 클래스
public class SignupRequestDto {

    // 속성
    private String username;
    private String email;
    private String password;

    // 셍성자
    public SignupRequestDto() {
    }

    // 기능
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
