package com.project.Rakshak.config;

import com.project.Rakshak.utils.ImageUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ImageUtils imageUtils() {
        return new ImageUtils();
    }
}
