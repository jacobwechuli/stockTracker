package com.stockportfolio.manager.service;

import com.stockportfolio.manager.model.Stock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface StockService {
    Stock saveStock(Stock stock);
    Optional<Stock> findBySymbol(String symbol);
    List<Stock> getAllStocks();
}
