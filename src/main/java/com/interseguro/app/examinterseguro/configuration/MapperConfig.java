package com.interseguro.app.examinterseguro.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class MapperConfig {

    @Bean("imageMapper")
    public ModelMapper imageMapper() {
        return new ModelMapper();
    }
}
