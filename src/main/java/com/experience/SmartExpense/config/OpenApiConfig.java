package com.experience.SmartExpense.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI smartExpenseOpenAPI() {

        final String securitySchemeName = "Bearer Authentication";

        return new OpenAPI()

                .info(new Info()
                        .title("SmartExpense API")
                        .version("1.0.0")
                        .description("""
                                API REST para la gestión de gastos personales.

                                Funcionalidades:
                                - Registro de usuarios
                                - Inicio de sesión con JWT
                                - Gestión de gastos
                                - Estadísticas
                                """)
                        .contact(new Contact()
                                .name("Tu Nombre")
                                .url("https://github.com/TU_USUARIO"))
                        .license(new License()
                                .name("MIT")))

                .externalDocs(new ExternalDocumentation()
                        .description("Repositorio del proyecto")
                        .url("https://github.com/TU_USUARIO/SmartExpense"))

                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))

                .schemaRequirement(
                        securitySchemeName,
                        new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"));
    }
}