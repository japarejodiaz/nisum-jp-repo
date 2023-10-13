package com.springboot.project.nisumprojectrestapp.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/users/getAll").permitAll()
                .antMatchers("/h2-console/**",
                        "/v2/api-docs/**",
                        "/swagger-ui/**",
                        "/configuration/**",
                        "/swagger-resources/**").permitAll()
                .anyRequest().authenticated();

        http.headers().frameOptions().sameOrigin();


    }

}
