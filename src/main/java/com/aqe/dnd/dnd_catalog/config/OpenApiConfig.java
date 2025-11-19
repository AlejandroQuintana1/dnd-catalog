package com.aqe.dnd.dnd_catalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("DND Backend API")
                        .version("1.0")
                        .description("API para gestionar personajes, misiones y sincronización en DND"));
    }
}