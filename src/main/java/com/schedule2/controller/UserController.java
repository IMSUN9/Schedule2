package com.schedule2.controller;

import com.schedule2.dto.UserCreateRequestDto;
import com.schedule2.dto.UserResponseDto;
import com.schedule2.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 유저 관련 요청을 가장 먼저 받는 컨트롤러 클래스
@RestController
@RequestMapping("/users")
public class UserController {

    // 속성

    private final UserService userService;

    // 생성자
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 기능
    // 유저 생성 요청을 처리하는 메서드
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserCreateRequestDto requestDto) {

        // 1. 서비스에게 유저 생성 작업 맡기기
        UserResponseDto responseDto = userService.createUser(requestDto);

        // 2. 생성 결과를 HTTP 응답 객체로 만들기
        ResponseEntity<UserResponseDto> responseEntity = ResponseEntity.ok(responseDto);

        // 3. 최종 응답 반환하기
        return responseEntity;
    }

    // 유저 전체 조회 요청을 처리하는 메서드
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {

        // 1. 서비스에게 유저 전체 조회 작업 맡기기
        List<UserResponseDto> responseDtoList = userService.getAllUsers();

        // 2. 조회 결과를 HTTP 응답 객체로 만들기
        ResponseEntity<List<UserResponseDto>> responseEntity = ResponseEntity.ok(responseDtoList);

        // 3. 최종 응답 반환하기
        return responseEntity;
    }

    // 유저 단건 조회 요청을 처리하는 메서드
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long userId) {

        // 1. 서비스에게 userId로 유저 조회 맡기기
        UserResponseDto responseDto = userService.getUserById(userId);

        // 2. 조회 결과를 HTTP 응답 객체로 만들기
        ResponseEntity<UserResponseDto> responseEntity = ResponseEntity.ok(responseDto);

        // 3. 최종 응답 반환하기
        return responseEntity;
    }



}
