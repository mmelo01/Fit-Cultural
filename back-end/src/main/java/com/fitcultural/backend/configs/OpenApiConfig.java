package com.fitcultural.backend.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Indicando que é uma classe que vai configurar o spring/sistema.
@Configuration
public class OpenApiConfig {

//  indica que o Spring deve gerir este method configuração e injetando as Dependências desse objeto automaticamente.
    @Bean
    public OpenAPI customOpenAPI(){
        //esse openapi é a config do swagger, aqui so estou a colocar algumas informações para aparecer na documentação.
        return new OpenAPI()
                .info(new Info()
                        .title("FitCultural")
                        .version("v1.0")
                        .description("### Time de Desenvolvimento:\n" +
                                "* **Gabriel Marques**\n" +
                                "* **Leonardo**\n" +
                                "* **Giovanni**\n" +
                                "* **Eduardo**\n\n" +
                                "API oficial para o ecossistema Fit Cultural.")
                        //.termsOfService() TODO seria muito massa fazer termos de uso, para informar como utilizar o app.
                        .contact(new Contact()
                                .name("Equipe de Desenvolvimento Fit Cultural")
                                .url("https://github.com/mmelo01/Fit-Cultural")));
    }
}
