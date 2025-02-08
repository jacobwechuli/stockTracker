package com.stockportfolio.manager.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.stockportfolio.manager.model.Stock;
import com.stockportfolio.manager.repository.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AlphaVantageService {
    private final WebClient webClient;
    private static final Logger logger = LoggerFactory.getLogger(AlphaVantageService.class);
    private final StockRepository stockRepository;


    @Value("${alpha.vantage.api.key}")
    private String apiKey;

    public AlphaVantageService(@Value("${alpha.vantage.base.url}") String baseUrl, WebClient.Builder webClientBuilder, StockRepository stockRepository) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
        this.stockRepository = stockRepository;
    }
    public Mono<String> getRealTimeStockPrice(String symbol) {
        Optional<Stock> cachedStock = stockRepository.findBySymbol(symbol);
        if (cachedStock.isPresent()) {
            Stock stock = cachedStock.get();
            return Mono.just("{\"symbol\": \"" + stock.getSymbol() + "\", \"price\": " + stock.getPrice() + ", \"lastupdated\": \"" + stock.getLastUpdated() + "\" }");
        }
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("function", "GLOBAL_QUOTE")
                        .queryParam("symbol", symbol)
                        .queryParam("apikey", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(response -> saveStockToDatabase(response, symbol));
    }

    private Mono<Void> saveStockToDatabase(String response, String symbol) {
        try {
            System.out.println("Alpha Vantage API Response: " + response);


            JSONObject json = new JSONObject(response);
            JSONObject quote = json.optJSONObject("Global Quote");
            if (quote == null || quote.isEmpty()) {
                System.err.println("No Global Quote found in response, skipping save");
                return Mono.empty();
            }
            System.out.println("Available keys in API response: ");
            for (String key : quote.keySet()) {
                System.out.println("Key: ["+  key + " | Value: " + quote.get(key) + "]");
            }
            Double price = 0.0;
            try {
                String priceKey = quote.keySet().stream()
                        .filter(k -> k.replaceAll("\\s", "").toLowerCase().contains("price"))
                        .findFirst()
                        .orElse(null);
                if (priceKey != null) {
                    price = Double.parseDouble(quote.optString(priceKey, "0.0").trim());
                    System.out.println("Price: " + price);
                } else {
                    System.err.println("No price found in API response, using default price 0.0");
                }
            } catch (Exception e) {
                System.err.println("Warning: Could not extract stock price. Error: " + e.getMessage());
            }
            if (price == null) {
                System.err.println("Price is null! Set it to 0.0");
                price = 0.0;
            }

            Double finalPrice = price;
            getCompanyName(symbol)
                    .defaultIfEmpty("unknown")
                    .subscribe(companyName -> {
                        Stock stock = new Stock(symbol, companyName, finalPrice, LocalDateTime.now());
                        stockRepository.save(stock);
                        System.out.println("Saved stock: " + stock);
                    });
        } catch (Exception e) {
            System.err.println("Error parsing stock data: " + e.getMessage());

            Stock stock = new Stock(symbol, "Unknown", 0.0, LocalDateTime.now());
            stockRepository.save(stock);
            System.out.println("Saved stock with default values: " + stock);
        }
        return null;
    }

    public Mono<String> getDailyStockData(String symbol) {
        String finalUrl = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("function", "TIME_SERIES_DAILY")
                        .queryParam("symbol", symbol)
                        .queryParam("apikey", apiKey)
                        .build())
                .toString();

        logger.info("Calling Alpha Vantage API with Url: {}" + finalUrl);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("function", "TIME_SERIES_DAILY")
                        .queryParam("symbol", symbol)
                        .queryParam("apikey", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }
    private Mono<String> getCompanyName(String symbol) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("function", "SYMBOL_SEARCH")
                        .queryParam("keywords", symbol)
                        .queryParam("apikey", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {

        try {

            JSONObject json = new JSONObject(response);
            if (json.has("bestMatches")) {
                JSONObject match = json.getJSONArray("bestMatches").getJSONObject(0);
                return match.getString("2. name");
            }
        } catch (Exception e) {
            System.out.println("Error fetching stock name: " + e.getMessage());
        }
        return "Unknown";
    });
}}
