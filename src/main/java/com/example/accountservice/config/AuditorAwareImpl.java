package com.example.accountservice.config;//package com.edu.springjwt.config;
//
//import com.edu.springjwt.security.services.UserPrincipal;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import java.util.Optional;
//
//@Component
//public class AuditorAwareImpl implements AuditorAware<String> {
//
//    @Override
//    public Optional<String> getCurrentAuditor() {
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null || !authentication.isAuthenticated()) {
//            return Optional.empty();
//        }
//        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
//        if (principal == null) return Optional.empty();
//        return Optional.of(principal.getEmail());
//    }
//}