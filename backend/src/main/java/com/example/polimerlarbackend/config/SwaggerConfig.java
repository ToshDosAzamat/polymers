package com.example.polimerlarbackend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Polymers API", version = "v1"),
        security = @SecurityRequirement(name = "JWT"),
        servers = {
                @Server(url = "https://polimerlar-backend.onrender.com", description = "Production Test Server"),
                @Server(url = "https://toshdosazamat-polimerlar-backend-ab3e.twc1.net", description = "Production MainServer"),
                @Server(url = "http://localhost:8080", description = "Local Development Server")
        }
)
@SecurityScheme(
        name = "JWT",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer"
)
public class SwaggerConfig {
}
