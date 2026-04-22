package com.schedule2.service;

import com.schedule2.dto.UserCreateRequestDto;
import com.schedule2.dto.UserResponseDto;
import com.schedule2.dto.UserUpdateRequestDto;
import com.schedule2.entity.User;
import com.schedule2.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// 유저 관련 비즈니스 로직을 처리하는 서비스 클래스
@Service
public class UserService {

    // 속성
    private UserRepository userRepository;

    // 생성자
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 기능
    // 유저 생성 기능
    public UserResponseDto createUser(UserCreateRequestDto requestDto) {

        // 1. 요청 값 꺼내기
        String username = requestDto.getUsername();
        String email = requestDto.getEmail();

        // 2. 요청값으로 User 엔티티 만들기
        User user = new User(username, email);

        // 3. DB에 저장하기
        User savedUser = userRepository.save(user);

        // 4. 저장된 결과를 응답 DTO로 바꾸기
        UserResponseDto userResponseDto = new UserResponseDto(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getCreatedAt(),
                savedUser.getUpdatedAt()
        );

        // 5. 응답 DTO 반환하기
        return userResponseDto;
    }

    // 유저 전체 조회 기능
    public List<UserResponseDto> getAllUsers() {

        // 1. DB에서 유저 전체 조회하기
        List<User> userList = userRepository.findAll();

        // 2. 응답 DTO들을 담을 빈 리스트 만들기
        List<UserResponseDto> responseDtoList = new ArrayList<>();

        // 3. 조회한 유저들을 하나씩 꺼내서 응답 DTO로 바꾸기
        for (User user : userList) {
            UserResponseDto userResponseDto = new UserResponseDto(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getCreatedAt(),
                    user.getUpdatedAt()
            );

            // 4. 만들어진 응답 DTO를 리스트에 담기
            responseDtoList.add(userResponseDto);
        }

        // 5. 최종 응답 리스트 반환하기
        return responseDtoList;
    }

    // 유저 단건 조회 기능
    public UserResponseDto getUserById(Long userId) {

        // 1. userId로 DB에서 유저 조회하기
        Optional<User> optionalUser = userRepository.findById(userId);

        // 2. 해당 유저가 없으면 예외 발셍시키기
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("해당 유저가 존재하지 않습니다.");
        }

        // 3. Optional 안에 들어있는 실제 User 꺼내기
        User user = optionalUser.get();

        // 4. 조회한 엔티티를 응답 DTO로 바꾸기
        UserResponseDto responseDto = new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );

        // 5. 응답 DTO 반환하기
        return responseDto;
    }

    // 유저 수정 기능
    @Transactional
    public UserResponseDto updateUser(Long userId, UserUpdateRequestDto requestDto) {

        // 1. userId로 기존 유저 조회하기
        Optional<User> optionalUser = userRepository.findById(userId);

        // 2. 해당 유저가 없으면 예외 발생시키기
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("해당 유저가 존재하지 않습니다.");
        }

        // 3. Optional 안에 들어있는 실제 User 꺼내기
        User user = optionalUser.get();

        // 4. 요청값 꺼내기
        String username = requestDto.getUsername();
        String email = requestDto.getEmail();

        // 5. 기존 유저 정보 수정하기
        user.updateUser(username, email);

        // 6. 수정된 엔티티를 응답 DTO로 바꾸기
        UserResponseDto responseDto = new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );

        //7. 응답 DTO 반환하기
        return responseDto;
    }

}
