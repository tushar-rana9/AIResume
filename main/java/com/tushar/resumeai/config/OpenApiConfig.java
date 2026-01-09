package com.tushar.resumeai.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI resumeAiOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Resume AI Analyzer API")
                        .description("AI-powered Resume Analyzer with ATS, Bias Detection and Job Matching")
                        .version("1.0.0"));
    }
}
