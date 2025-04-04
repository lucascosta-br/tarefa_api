package com.github.lucascostabr.tarefa_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI configOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API RESTful de Gestão de Tarefas")
                        .version("v1")
                        .description("Gestão de Tarefas")
                        .termsOfService("https://github.com/lucascosta-br")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/lucascosta-br")));
    }

}
