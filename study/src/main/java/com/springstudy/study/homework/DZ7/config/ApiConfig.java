package com.springstudy.study.homework.DZ7.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {
    @Bean
    public OpenAPI libraryProject(){
        return new OpenAPI()
                .info(new Info()
                        .title("Фильмы")
                        .description("Серфис по покупке и аренде фильмов")
                        .version("0.1")
                        .contact(new Contact().name("Koshelev Evgenii"))
                );
    }
}
