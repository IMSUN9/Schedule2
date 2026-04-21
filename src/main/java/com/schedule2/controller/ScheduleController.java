package com.schedule2.controller;

import com.schedule2.dto.ScheduleCreateRequestDto;
import com.schedule2.dto.ScheduleResponseDto;
import com.schedule2.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 일정 관련 요청을 가장 먼저 받는 컨트롤러 클래스
@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    // 속성
    // 일정 관련 작업을 처리할 서비스
    private ScheduleService scheduleService;

    // 생성자
    // 컨틍롤러가 서비스 객체를 전달받는 생성자
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 기능
    // 일정 생성 요청을 처리하는 메서드
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleCreateRequestDto requestDto) {
        ScheduleResponseDto responseDto = scheduleService.createSchedule(requestDto);
        return ResponseEntity.ok(responseDto);
    }
}
