package com.bms.fakestoreapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class OpenApiConfig {

    @Value("${api.docs.title}")
    private String appTitle;
    @Value("${api.docs.version}")
    private String appVersion;
    @Value("${api.docs.description}")
    private String appDescription;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title(appTitle)
                        .version(appVersion)
                        .description(appDescription)
                );
    }
}