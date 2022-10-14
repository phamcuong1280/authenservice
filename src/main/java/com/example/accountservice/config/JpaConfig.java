package com.example.applicationgateway.config;//package com.edu.springjwt.config;
//
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableJpaAuditing(auditorAwareRef = "auditorAware")
//@EnableJpaRepositories(basePackages = {"com.edu.*"})
//@EntityScan(basePackages = {"com.edu.*"})
//public class JpaConfig {
//    @Bean
//    public AuditorAware<String> auditorAware() {
//        return new AuditorAwareImpl();
//    }
//}
