package com.stockportfolio.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/stocks")
public class AlphaStockController {
    @Autowired
    private AlphaStockController alphaStockController;

    @GetMapping("/price/{symbol}")
    public Mono<String> getRealTimeStockPrice(@PathVariable("symbol") String symbol) {
        return alphaStockController.getRealTimeStockPrice(symbol);
    }
    @GetMapping("/daily/{symbol}")
    public Mono<String> getDailyStockData(@PathVariable("symbol") String symbol) {
        return alphaStockController.getDailyStockData(symbol);
    }
}
