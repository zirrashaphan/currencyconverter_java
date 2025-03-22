
package com.example.currencyconverter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

/**
 * Service class handling currency conversion operations
 * Uses ExchangeRate API to fetch current exchange rates
 */
@Service
public class CurrencyService {
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";
    private static final Logger logger = LoggerFactory.getLogger(CurrencyService.class);
    private final RestTemplate restTemplate;

    @Autowired
    public CurrencyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Converts an amount from one currency to another
     * @param from Source currency code
     * @param to Target currency code
     * @param amount Amount to convert
     * @return Converted amount in target currency
     * @throws RuntimeException if conversion fails
     */
    public double convert(String from, String to, double amount) {
        String url = API_URL + from;
        logger.info("Fetching exchange rates from: {}", url);

        try {
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            if (response == null) {
                logger.error("API response is null for currency: {}", from);
                throw new RuntimeException("Unable to fetch exchange rates");
            }

            logger.debug("API response: {}", response);
            if (response.containsKey("rates")) {
                Map<String, Double> rates = (Map<String, Double>) response.get("rates");
                Double rate = rates.get(to);
                if (rate != null) {
                    logger.info("Conversion rate from {} to {}: {}", from, to, rate);
                    return rate * amount;
                } else {
                    logger.error("Currency {} not found in rates", to);
                    throw new RuntimeException("Target currency not supported");
                }
            } else {
                logger.error("No 'rates' field in API response: {}", response);
                throw new RuntimeException("Invalid API response format");
            }
        } catch (Exception e) {
            logger.error("Error during currency conversion: {}", e.getMessage(), e);
            throw new RuntimeException("Currency conversion failed: " + e.getMessage());
        }
    }
}
