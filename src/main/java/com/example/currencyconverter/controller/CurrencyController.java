package com.example.currencyconverter.controller;

import com.example.currencyconverter.service.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("https://currency-converter.your-username.repl.co")
public class CurrencyController {
    private static final Logger logger = LoggerFactory.getLogger(CurrencyController.class);
    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public double convert(@RequestParam String from, @RequestParam String to, @RequestParam double amount) {
        logger.info("Received conversion request: {} to {} for amount {}", from, to, amount);
        try {
            return currencyService.convert(from, to, amount);
        } catch (RuntimeException e) {
            logger.error("Conversion failed: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}