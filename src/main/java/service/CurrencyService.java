package com.example.currencyconverter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class CurrencyService {
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";
    private final RestTemplate restTemplate;

    @Autowired
    public CurrencyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double convert(String from, String to, double amount) {
        String url = API_URL + from;
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        if (response != null && response.containsKey("rates")) {
            Map<String, Double> rates = (Map<String, Double>) response.get("rates");
            Double rate = rates.get(to);
            if (rate != null) {
                return rate * amount;
            }
        }
        throw new RuntimeException("Invalid currency or unable to fetch exchange rate");
    }
}