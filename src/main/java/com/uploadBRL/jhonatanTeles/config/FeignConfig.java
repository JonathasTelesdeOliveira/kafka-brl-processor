package com.uploadBRL.jhonatanTeles.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public FeingError feignError() {
        return new FeingError();
    }
}
