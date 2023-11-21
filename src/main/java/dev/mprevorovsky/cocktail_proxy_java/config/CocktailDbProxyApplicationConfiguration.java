package dev.mprevorovsky.cocktail_proxy_java.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Setup of beans required by the application.
 */
@Configuration
public class CocktailDbProxyApplicationConfiguration {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
