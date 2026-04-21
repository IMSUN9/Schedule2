package com.schedule2.service;

import com.schedule2.dto.ScheduleCreateRequestDto;
import com.schedule2.dto.ScheduleResponseDto;
import com.schedule2.entity.Schedule;
import com.schedule2.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

// 일정 관련 비즈니스 로직을 처리하는 서비스 클래스
@Service
public class ScheduleService {

    // 속성
    private final ScheduleRepository scheduleRepository;

    // 생성자
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // 기능

    // 일정 생성 기능
    public ScheduleResponseDto createSchedule(ScheduleCreateRequestDto requestDto) {

        // 1. 요청 값 꺼내기
        String username = requestDto.getUsername();
        String title = requestDto.getTitle();
        String contents = requestDto.getContents();

        // 2. 요청값으로 Schedule 엔티티 만들기
        Schedule schedule = new Schedule(username, title, contents);

        // 3. DB에 저장하기
        Schedule savedSchedule = scheduleRepository.save(schedule);

        // 4. 저장된 결과를 응답 DTO로 만들기
        ScheduleResponseDto responseDto = new ScheduleResponseDto(
                savedSchedule.getId(),
                savedSchedule.getUsername(),
                savedSchedule.getTitle(),
                savedSchedule.getContents(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getUpdatedAt()
        );

        // 5. 응답 DTO로 반환하기
        return responseDto;
    }
}
