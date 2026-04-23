package com.schedule2.controller;

import com.schedule2.dto.ScheduleCreateRequestDto;
import com.schedule2.dto.ScheduleResponseDto;
import com.schedule2.dto.ScheduleUpdateRequestDto;
import com.schedule2.service.ScheduleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 일정 관련 요청을 가장 먼저 받는 컨트롤러 클래스
@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    // 속성
    // 일정 관련 작업을 처리할 서비스
    private ScheduleService scheduleService;

    // 생성자
    // 컨트롤러가 서비스 객체를 전달받는 생성자
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 기능
    // 일정 생성 요청을 처리하는 메서드
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleCreateRequestDto requestDto, HttpSession session) {

        // 1. 세션에서 로그인 유저 ID 꺼내기
        Long loginUserId = (Long) session.getAttribute("loginUserId");

        // 2. 로그인하지 않은 경우 예외 발생시키기
        if (loginUserId == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        // 3. 서비스에게 로그인 유저 기준 일정 생성 맡기기
        ScheduleResponseDto responseDto = scheduleService.createScheduleWithLoginUser(loginUserId, requestDto);

        // 4. 생성 결과를 HTTP 응답 객체로 만들기
        ResponseEntity<ScheduleResponseDto> responseEntity = ResponseEntity.ok(responseDto);

        // 5. 최종 응답 반환하기
        return responseEntity;
    }

    // 일정 전체 조회 요청을 처리하는 메서드
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules() {

        // 1. 서비스에게 일정 전체 조회 작업 맡기기
        List<ScheduleResponseDto> responseDtoList = scheduleService.getAllSchedules();

        // 2. 조회 결과를 HTTP 응답 객체로 만들기
        ResponseEntity<List<ScheduleResponseDto>> responseEntity = ResponseEntity.ok(responseDtoList);

        // 3. 최종 응답 반환하기
        return responseEntity;
    }

    // 일정 단건 조회 요청을 처리하는 메서드
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> getScheduleById(@PathVariable Long scheduleId) {

        // 1. 서비스에게 scheduleId로 일정 조회 맡기기
        ScheduleResponseDto responseDto = scheduleService.getScheduleById(scheduleId);

        // 2. 조회 결과를 HTTP 응답 객체로 만들기
        ResponseEntity<ScheduleResponseDto> responseEntity = ResponseEntity.ok(responseDto);

        // 3. 최종 응답 반환하기
        return responseEntity;
    }

    // 일정 수정 요청을 처리하는 메서드
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleUpdateRequestDto requestDto, HttpSession session) {

       // 1. 세션에서 로그인 유저 ID 꺼내기
        Long loginUserId = (Long) session.getAttribute("loginUserId");

        // 2. 로그인하지 않은 경우 예외 발생시키기
        if (loginUserId == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        // 3. 서비스에게 로그인 유저 기준 일정 수정 맡기기
        ScheduleResponseDto responseDto = scheduleService.updateScheduleWithLoginUser(scheduleId, loginUserId, requestDto);

        // 4. 수정 결과를 HTTP 응답 객체로 만들기
        ResponseEntity<ScheduleResponseDto> responseEntity = ResponseEntity.ok(responseDto);

        // 5. 최종 응답 반환하기
        return responseEntity;
    }

    // 일정 삭제 요청을 처리하는 메서드
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId, HttpSession session) {

        // 1. 세션에서 로그인 유저 ID 꺼내기
        Long loginUserId = (Long) session.getAttribute("loginUserId");

        // 2. 로그인하지 않은 경우 예외 발생시키기
        if (loginUserId == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        // 3. 서비스에게 로그인 유저 기준 일정 삭제 맡기기
        scheduleService.deleteScheduleWithLoginUser(scheduleId, loginUserId);

        // 4. 내용 없는 성공 응답 객체 만들기
        ResponseEntity<Void> responseEntity = ResponseEntity.noContent().build();

        // 5. 최종 응답 반환하기
        return responseEntity;
    }
}
