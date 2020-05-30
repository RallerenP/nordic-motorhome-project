package com.nordicmotorhome.motorhomerentals.view.config;

import com.nordicmotorhome.motorhomerentals.view.interceptors.AuthenticationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor());
    }
}
