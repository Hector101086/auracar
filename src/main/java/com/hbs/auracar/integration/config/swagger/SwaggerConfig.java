package com.hbs.auracar.integration.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
            .info( new Info().title( "Auracar" )
                .version( "v0.0.1" ) );
    }

    @Bean
    public GroupedOpenApi carsGroupApi() {
        return GroupedOpenApi.builder()
            .group( "car" )
            .pathsToMatch( "/api/v1/**" )
            .build();
    }

    @Bean
    public GroupedOpenApi errorsGroupApi() {
        return GroupedOpenApi.builder()
            .group( "error" )
            .pathsToMatch( "/error/codes/**" )
            .build();
    }
}
