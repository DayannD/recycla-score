package com.simplon.recyclascore.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Permet CORS pour toutes les routes
                        .allowedOrigins("http://localhost:4200")  // Permet l'origine spécifiée
                        .allowedMethods("*");  // Permet toutes les méthodes HTTP
            }
        };
    }
}
