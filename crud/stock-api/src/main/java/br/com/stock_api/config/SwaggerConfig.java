package br.com.stock_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Stock API")
                        .version("0.0.1")
                        .description("API Restful para gerenciamento de estoque, desenvolvida com Spring Boot. Contempla cadastro de produtos, registro de entradas, saídas e transferências."));
    }
}
