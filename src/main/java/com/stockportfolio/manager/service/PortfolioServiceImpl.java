package com.stockportfolio.manager.service;

import com.stockportfolio.manager.model.Portfolio;
import com.stockportfolio.manager.model.Stock;
import com.stockportfolio.manager.model.User;
import com.stockportfolio.manager.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PortfolioServiceImpl implements PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepository;

    @Override
    public Portfolio addStockToPortfolio(User user, Stock stock, int quantity, double purchasePrice) {
        Optional<Portfolio> existingEntry = portfolioRepository.findByUserAndStock(user, stock);
        if (existingEntry.isPresent()) {
            Portfolio portfolio = existingEntry.get();
            portfolio.setQuantity(portfolio.getQuantity() + quantity);
            return portfolioRepository.save(portfolio);
        } else {
            Portfolio portfolio = new Portfolio();
            portfolio.setUser(user);
            portfolio.setStock(stock);
            portfolio.setQuantity(quantity);
            portfolio.setPurchasePrice(purchasePrice);
            return portfolioRepository.save(portfolio);
        }
    }
    @Override
    public List<Portfolio> getUserPortfolio(User user) {
        return portfolioRepository.findByUser(user);
    }
    @Override
    public void removeStockFromPortfolio(User user, Stock stock) {
        portfolioRepository.findByUserAndStock(user, stock).ifPresent(portfolioRepository::delete);
    }
    @Override
    public Optional<Portfolio> findByUserAndStock(User user, Stock stock) {
        return portfolioRepository.findByUserAndStock(user, stock);
    }
}
