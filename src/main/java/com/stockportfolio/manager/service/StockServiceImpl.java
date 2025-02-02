package com.stockportfolio.manager.service;

import com.stockportfolio.manager.model.Stock;
import com.stockportfolio.manager.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockRepository stockRepository;

    @Override
    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }
    @Override
    public Optional<Stock> findBySymbol(String symbol) {
        return stockRepository.findBySymbol(symbol);
    }
    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }
}
