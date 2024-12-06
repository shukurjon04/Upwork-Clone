package com.example.onlinejobs.Audit;

import com.example.onlinejobs.Entity.MainUserTable.UserJobs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class Auditing {
    @Bean
    public AuditorAware<UserJobs> auditorProvider() {
        return new AuditingSecurity();
    }
}
