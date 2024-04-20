package com.assignment.bankingservice.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for OpenAPI documentation.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Customer Account API")
                        .version("0.0.1-SNAPSHOT")
                        .description("This API facilitates account management for banking services. It supports operations for opening new accounts with an initial credit option, and retrieving user information including account balances and transaction history. Endpoints include account creation with optional initial credit and user information retrieval showcasing name, surname, balance, and transactions."));
    }
}