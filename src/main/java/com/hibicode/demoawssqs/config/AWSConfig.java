package com.hibicode.demoawssqs.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AWSConfig {

    @Bean
    @Primary
    public AWSCredentialsProvider credentials() {
        return new ProfileCredentialsProvider("app");
    }
}
