package com.stockportfolio.manager.service;

import com.stockportfolio.manager.model.Portfolio;
import com.stockportfolio.manager.model.Stock;
import com.stockportfolio.manager.model.User;

import java.util.List;
import java.util.Optional;

public interface PortfolioService {
    Portfolio addStockToPortfolio(User user, Stock stock, int quantity, double purchasePrice);
    List<Portfolio> getUserPortfolio(User user);
    Optional<Portfolio> findByUserAndStock(User user, Stock stock);
    void removeStockFromPortfolio(User user, Stock stock);
}
