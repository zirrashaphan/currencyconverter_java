package com.example.currencyconverter.service;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CurrencyServiceTest {

    @Test
    public void testConvert() {
        // Mock RestTemplate
        RestTemplate restTemplate = mock(RestTemplate.class);

        // Prepare mock response
        Map<String, Object> response = new HashMap<>();
        Map<String, Double> rates = new HashMap<>();
        rates.put("EUR", 0.85);
        response.put("rates", rates);

        // Mock the API call
        when(restTemplate.getForObject(anyString(), eq(Map.class))).thenReturn(response);

        // Create service instance with mocked RestTemplate
        CurrencyService currencyService = new CurrencyService(restTemplate);

        // Test conversion
        double result = currencyService.convert("USD", "EUR", 100);
        assertEquals(85.0, result, 0.001, "Conversion should return correct amount");
    }
}