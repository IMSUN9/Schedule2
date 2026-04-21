package com.schedule2.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    // 속성

    // 처음 저장될 때 자동으로 저장 시간 들어감
    @CreatedDate
    private LocalDateTime createdAt;

    // 수정될 때 자동으로 수정 시간 들어감
    @LastModifiedDate
    private LocalDateTime updatedAt;

    // 생성자
    // BaseEntity는 직접 객체를 만들지 않는 부모 클래스이므로 생성자 작성하지 않아도 됨

    // 기능

    // 생성 시간 조회 메서드
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // 수정 시간 조회 메서드
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
