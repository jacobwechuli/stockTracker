package com.stockportfolio.manager.controller;

import com.stockportfolio.manager.model.Portfolio;
import com.stockportfolio.manager.model.Stock;
import com.stockportfolio.manager.model.User;
import com.stockportfolio.manager.service.PortfolioService;
import com.stockportfolio.manager.service.StockService;
import com.stockportfolio.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {
    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private StockService stockService;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> addStockToPortfolio(@RequestParam String username,
                                                 @RequestParam String symbol,
                                                 @RequestParam int quantity,
                                                 @RequestParam double purchasePrice) {
        Optional<User> user = userService.findByUsername(username);
        Optional<Stock> stock = stockService.findBySymbol(symbol);

        if (user.isEmpty() || stock.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid username or stock symbol");
        }
        Portfolio portfolio = portfolioService.addStockToPortfolio(user.get(), stock.get(), quantity, purchasePrice);
        return ResponseEntity.ok(portfolio);
    }
    @GetMapping("/{username}")
    public ResponseEntity<List<Portfolio>> getUserPortfolio(@PathVariable String username) {
        Optional<User> user = userService.findByUsername(username);
        return user.map(value -> ResponseEntity.ok(portfolioService.getUserPortfolio(value)))
                .orElse(ResponseEntity.badRequest().build());
    }
    @DeleteMapping("/remove")
    public ResponseEntity<?> removeStockFromPortfolio(@RequestParam String username, @RequestParam String symbol) {
        Optional<User> user = userService.findByUsername(username);
        Optional<Stock> stock = stockService.findBySymbol(symbol);
        if (user.isEmpty() || stock.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid username or stock symbol");
        }
        portfolioService.removeStockFromPortfolio(user.get(), stock.get());
        return ResponseEntity.ok("Stock removed from portfolio");
    }

}
