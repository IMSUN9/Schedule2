package com.schedule2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
// 유저 정보를 저장하는 엔티티 클래스
public class User extends BaseEntity {

    // 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    // 생성자
    // JPA 용
    public User() {
    }

    // 개발자용
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    // 기능
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    // 유저 정보 수정 기능
    public void updateUser(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
