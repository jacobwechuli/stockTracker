package com.stockportfolio.manager.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AlphaVantageTestService {
    private final RestTemplate restTemplate = new RestTemplate();

    public String getDailyStockData(String symbol) {
        String apiKey = "VGDT9ZCQWZ1FC6JK";
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol + "&apikey=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }
}
