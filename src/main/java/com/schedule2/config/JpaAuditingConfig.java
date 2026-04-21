package com.schedule2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// JPA Auditing 기능을 켜는 설정 클래스 (createdAt, updatedAt 자동으로 넣어주는 기능)
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {
}
