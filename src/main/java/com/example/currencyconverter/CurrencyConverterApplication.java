
package com.example.currencyconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Main Spring Boot application class for the Currency Converter
 * Initializes the application context and defines beans
 */
@SpringBootApplication
public class CurrencyConverterApplication {
    public static void main(String[] args) {
        SpringApplication.run(CurrencyConverterApplication.class, args);
    }

    /**
     * Creates a RestTemplate bean for making HTTP requests
     * @return RestTemplate instance
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
