package com.schedule2.service;

import com.schedule2.dto.UserCreateRequestDto;
import com.schedule2.dto.UserResponseDto;
import com.schedule2.entity.User;
import com.schedule2.repository.UserRepository;
import org.springframework.stereotype.Service;

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
}
