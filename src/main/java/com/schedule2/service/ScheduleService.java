package com.schedule2.service;

import com.schedule2.dto.ScheduleCreateRequestDto;
import com.schedule2.dto.ScheduleResponseDto;
import com.schedule2.dto.ScheduleUpdateRequestDto;
import com.schedule2.entity.Schedule;
import com.schedule2.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    // 일정 단건 조회 기능
    public ScheduleResponseDto getScheduleById(Long scheduleId) {

        // 1. scheduleId로 DB에서 일정 조회하기
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);

        // 2. 조회 결과가 있는지 확인하기
        if (optionalSchedule.isEmpty()) {
            throw new IllegalArgumentException("해당 일정이 존재하지 않습니다.");
        }

        // 3. Optional 안에 들어있는 실제 Schedule 꺼내기
        Schedule schedule = optionalSchedule.get();

        // 4. 조회한 엔티티를 응답 DTO로 바꾸기
        ScheduleResponseDto responseDto = new ScheduleResponseDto(
                schedule.getId(),
                schedule.getUsername(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
        // 5. 응답 DTO 반환하기
        return responseDto;
    }

    // 일정 수정 기능
    @Transactional
    public ScheduleResponseDto updateSchedule(Long scheduleId, ScheduleUpdateRequestDto requestDto) {

        // 1. scheduleId로 기존 일정 조회하기
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);

        // 2. 해당 일정이 없으면 예외 발생시키기
        if (optionalSchedule.isEmpty()) {
            throw new IllegalArgumentException("해당 일정이 존재하지 않습니다.");
        }

        // 3. Optional 안에 들어있는 실제 Schedule 꺼내기
        Schedule schedule = optionalSchedule.get();

        // 4. 요청 값 꺼내기
        String username = requestDto.getUsername();
        String title = requestDto.getTitle();
        String contents = requestDto.getContents();

        // 5. 기존 일정 내용 수정하기
        schedule.updateSchedule(username, title, contents);

        // 6. 수정된 엔티티를 응답 DTO로 바꾸기
        ScheduleResponseDto responseDto = new ScheduleResponseDto(
                schedule.getId(),
                schedule.getUsername(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
        // 7. 응답 DTO 반환하기
        return responseDto;
    }

    // 일정 삭제 기능
    public void deleteSchedule(Long scheduleId) {

        // 1. 해당 일정이 실제로 존재하는지 확인하기
        boolean exists = scheduleRepository.existsById(scheduleId);

        // 2. 존재하지 않으면 예외 발생시키기
        if (!exists) {
            throw new IllegalArgumentException("해당 일정이 존재하지 않습니다.");
        }

        // 3. scheduleId로 일정 삭제하기
        scheduleRepository.deleteById(scheduleId);
    }

}
