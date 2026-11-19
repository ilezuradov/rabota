package org.example.di;

import org.example.datasource.mapper.GameMapper;
import org.example.datasource.repository.GameRepository;
import org.example.domain.service.GameService;
import org.example.domain.service.GameServiceImpl;
import org.example.web.mapper.WebGameMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public GameRepository gameRepository() {
        return new GameRepository();
    }

    @Bean
    public GameMapper gameMapper() {
        return new GameMapper();
    }

    @Bean
    public WebGameMapper webGameMapper() {
        return new WebGameMapper();
    }

    @Bean
    public GameService gameService() {
        return new GameServiceImpl(gameRepository(), gameMapper());
    }
}