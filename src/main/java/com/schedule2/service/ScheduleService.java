package com.schedule2.service;

import com.schedule2.dto.ScheduleCreateRequestDto;
import com.schedule2.dto.ScheduleResponseDto;
import com.schedule2.dto.ScheduleUpdateRequestDto;
import com.schedule2.entity.Schedule;
import com.schedule2.entity.User;
import com.schedule2.repository.ScheduleRepository;
import com.schedule2.repository.UserRepository;
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

    // 유저 데이터를 조회할 레포지토리
    private final UserRepository userRepository;

    // 생성자
    // 서비스가 레포지토리 객체들을 전달받는 생성자
    public ScheduleService(ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    // 기능

    // 일정 생성 기능
    public ScheduleResponseDto createSchedule(ScheduleCreateRequestDto requestDto) {

       // 1. 요청값에서 userId 꺼내기
        Long userId = requestDto.getUserId();

        // 2. userId로 유저 조회하기
        Optional<User> optionalUser = userRepository.findById(userId);

        // 3. 해당 유저가 없으면 예외 발생시키기
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("해당 유저가 존재하지 않습니다.");
        }

        // 4. Optional 안에 들어있는 실제 User 꺼내기
        User user = optionalUser.get();

        // 5. 조회한 유저명 꺼내기
        String username = user.getUsername();

        // 6. 나머지 요청값 꺼내기
        String title = requestDto.getTitle();
        String contents = requestDto.getContents();

        // 7. Schedule 엔티티 만들기
        Schedule schedule = new Schedule(username, title, contents);

        // 8. Schedule 과 User 연결하기
        schedule.setUser(user);

        // 9. DB에 저장하기
        Schedule savedSchedule = scheduleRepository.save(schedule);

        // 10. 저장 결과를 응답 DTO로 바꾸기
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(
                savedSchedule.getId(),
                savedSchedule.getUsername(),
                savedSchedule.getTitle(),
                savedSchedule.getContents(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getUpdatedAt()
        );

        // 11. 응답 DTO 반환하기
        return scheduleResponseDto;
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

        // 4. 요청 값에서 userId 꺼내기
        Long userId = requestDto.getUserId();

        // 5. userId로 유저 조회하기
        Optional<User> optionalUser = userRepository.findById(userId);

        // 6. 해당 유저가 없으면 예외 발생시키기
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("해당 유저가 존재하지 않습니다.");
        }

        // 7. Optional 안에 들어있는 실제 User 꺼내기
        User user = optionalUser.get();

        // 8. 조회한 유저명 꺼내기
        String username = user.getUsername();

        // 9. 나머지 요청값 꺼내기
        String title = requestDto.getTitle();
        String contents = requestDto.getContents();

        // 10. 기존 일정 내용 수정하기
        schedule.updateSchedule(username, title, contents);

        // 11. 일정과 유저 다시 연결하기
        schedule.setUser(user);

        // 12. 수정된 엔티티를 응답 DTO로 바꾸기
        ScheduleResponseDto responseDto = new ScheduleResponseDto(
                schedule.getId(),
                schedule.getUsername(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );

        // 13. 응답 DTO 반환하기
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
