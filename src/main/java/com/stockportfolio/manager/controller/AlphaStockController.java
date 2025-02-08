package com.stockportfolio.manager.controller;

import com.stockportfolio.manager.service.AlphaVantageService;
import com.stockportfolio.manager.service.AlphaVantageTestService;
import com.stockportfolio.manager.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/stocks")
public class AlphaStockController {

    private final AlphaVantageService alphaVantageService;
    @Autowired
    public AlphaStockController(AlphaVantageService alphaVantageService) {
        this.alphaVantageService = alphaVantageService;
    }


    @GetMapping("/price/{symbol}")
    public Mono<String> getRealTimeStockPrice(@PathVariable String symbol) {
        return alphaVantageService.getRealTimeStockPrice(symbol);
    }

    @GetMapping("/daily/{symbol}")
    public Mono<String> getDailyStockData(@PathVariable("symbol") String symbol) {
        return alphaVantageService.getDailyStockData(symbol);
    }

}
