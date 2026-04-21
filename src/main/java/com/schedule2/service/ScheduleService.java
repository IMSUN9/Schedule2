package com.schedule2.service;

import com.schedule2.dto.ScheduleCreateRequestDto;
import com.schedule2.dto.ScheduleResponseDto;
import com.schedule2.entity.Schedule;
import com.schedule2.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    // 일정 전체 조회 기능
    public List<ScheduleResponseDto> getAllSchedules() {

        // 1. DB에서 일정 전체 조회하기
        List<Schedule> scheduleList = scheduleRepository.findAll();

        // 2. 응답 DTO들을 담을 빈 리스트 만들기
        List<ScheduleResponseDto> responseDtoList = new ArrayList<>();

        // 3. 조회한 일정들을 하나씩 꺼내서 응답 DTO로 바꾸기
        for (Schedule schedule : scheduleList) {
            ScheduleResponseDto responseDto = new ScheduleResponseDto(
                    schedule.getId(),
                    schedule.getUsername(),
                    schedule.getTitle(),
                    schedule.getContents(),
                    schedule.getCreatedAt(),
                    schedule.getUpdatedAt()
            );
            // 4. 새로 만들어진 응답 DTO를 리스트에 담기
            responseDtoList.add(responseDto);
        }
        // 5. 최종 응답 리스트 반환하기
        return responseDtoList;
    }
}
