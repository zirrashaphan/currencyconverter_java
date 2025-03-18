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
        RestTemplate restTemplate = mock(RestTemplate.class);
        Map<String, Object> response = new HashMap<>();
        Map<String, Double> rates = new HashMap<>();
        rates.put("EUR", 0.85);
        response.put("rates", rates);

        when(restTemplate.getForObject(anyString(), eq(Map.class))).thenReturn(response);

        CurrencyService currencyService = new CurrencyService(restTemplate);
        double result = currencyService.convert("USD", "EUR", 100);
        assertEquals(85.0, result, 0.001, "Conversion should return correct amount");
    }
}