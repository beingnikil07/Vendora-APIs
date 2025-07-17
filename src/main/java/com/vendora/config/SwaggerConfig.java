package com.vendora.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
        info = @Info(
                title = "Vendora App APIs",
                version = "1.0",
                description ="Api documentation for vendora API",
                contact = @Contact(
                        name = "Nikhil Kumar Rana",
                        email = "kumarnikil220@gmail.com"
                )
        )
)
@Configuration
public class SwaggerConfig {

}
