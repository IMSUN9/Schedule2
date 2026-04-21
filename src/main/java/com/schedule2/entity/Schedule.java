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

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    // 생성자

    // jpa용
    public Schedule() {}

    // 일정 생성 시 사용하는 생성자
    public Schedule(String username, String title, String contents) {
        this.username = username;
        this.title = title;
        this.contents = contents;
    }


    // 기능

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    // 일정 수정 기능
    public void updateSchedule(String username, String title, String contents) {
        this.username = username;
        this.title = title;
        this.contents = contents;
    }
}
