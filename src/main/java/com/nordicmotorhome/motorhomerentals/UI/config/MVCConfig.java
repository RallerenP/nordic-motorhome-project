package com.nordicmotorhome.motorhomerentals.UI.config;

import com.nordicmotorhome.motorhomerentals.UI.interceptors.AuthenticationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Author : RAP
//Used for checking our session every time we load or reload a page
@Configuration
public class MVCConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor());
    }
}
