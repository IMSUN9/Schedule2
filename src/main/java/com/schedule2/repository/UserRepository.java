package com.schedule2.repository;

import com.schedule2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// User 엔티티를 데이터베이스에 저장하고 조회하는 Repository 인터페이스
public interface UserRepository extends JpaRepository<User,Long> {


}
