package com.example.onlinejobs.Audit;

import com.example.onlinejobs.Entity.MainUserTable.UserJobs;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditingSecurity implements AuditorAware<UserJobs> {

    @Override
    public Optional<UserJobs> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }
        return Optional.ofNullable((UserJobs) authentication.getPrincipal());
    }
}
