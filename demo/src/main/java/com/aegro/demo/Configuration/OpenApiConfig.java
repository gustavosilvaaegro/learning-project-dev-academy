package com.aegro.demo.Configuration;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("MiniAPP API")
                            .version("1.0.0")
                            .description("API documentation for the project MiniAPP")
                            .termsOfService("")
                            .license(new io.swagger.v3.oas.models.info.License()
                                    .name("")
                                    .url("")
                            )
                );
    }
}
