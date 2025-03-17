package com.example.currencyconverter.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.matchesPattern;

@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testConvertEndpoint() throws Exception {
        mockMvc.perform(get("/convert")
                .param("from", "USD")
                .param("to", "EUR")
                .param("amount", "100"))
                .andExpect(status().isOk())
                .andExpect(content().string(matchesPattern("\\d+\\.\\d+")));
    }
}