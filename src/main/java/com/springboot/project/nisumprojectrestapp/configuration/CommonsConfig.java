package com.springboot.project.nisumprojectrestapp.configuration;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages={"com.springboot.project.nisumprojectrestapp.configuration" +
        "com.springboot.project.nisumprojectrestapp.service" +
        "com.springboot.project.nisumprojectrestapp.mapper" +
        "com.springboot.project.nisumprojectrestapp.repository"})
public class CommonsConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
