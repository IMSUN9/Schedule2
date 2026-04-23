package com.schedule2.entity;

import jakarta.persistence.*;

// 일정 정보를 저장하는 엔티티 클래스
@Entity
@Table(name = "schedules")
public class Schedule extends BaseEntity {

    // 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 일정과 연결된 유저 엔티티
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    // 생성자
    // jpa용
    public Schedule() {}

    // User 엔티티를 바로 받아서 일정을 생성하는 생성자
    public Schedule(User user, String title, String contents) {
        this.user = user;
        this.title = title;
        this.contents = contents;
    }

    // 기능
    public Long getId() {
        return id;
    }

    public String getUsername() {
        if (user == null) {
            return null;
        }

        return user.getUsername();
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    // 연결된 유저 조회
    public User getUser() {
        return user;
    }

    // 연결된 유저 ID 조회
    public Long getUserId() {
        if (user == null) {
            return null;
        }

        return user.getId();
    }

    // 연결된 유저명 조회
    public String getUserNameFromUser() {
        if (user == null) {
            return null;
        }

        return user.getUsername();
    }

    // 일정과 유저를 연결하는 기능
    public void setUser(User user) {
        this.user = user;
    }

    // User 엔티티를 바로 받아서 일정 정보를 수정하는 기능
    public void updateSchedule(User user, String title, String contents) {
        this.user = user;
        this.title = title;
        this.contents = contents;
    }

}
