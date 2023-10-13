package com.springboot.project.nisumprojectrestapp.configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    /**
     * Para la cabecera del documento generado par openapi / swagger.
     * Datos generales del servidor, desarrolladores, contacto, etc.
     * @return
     */
    @Bean
    public OpenAPI getOpenApiDefinition(){
        return new OpenAPI()
                .info(new Info()
                        .title("Nisum Application test")
                        .version("1.0")
                );
    }
}
