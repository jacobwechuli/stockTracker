package com.stockportfolio.manager.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "stocks")
@NoArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String symbol;
    @Column(nullable = false)
    private String name;
    @Column(name = "current_price", nullable = false)
    private Double price;
    private LocalDateTime lastUpdated;

    public Stock(Long id, String symbol, String name, Double price, LocalDateTime lastUpdated) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
        this.price = (price == null) ? price : 0.0;
        this.lastUpdated = lastUpdated;
    }

    public Stock(String symbol, String companyName, Double finalPrice, LocalDateTime now) {
        this.symbol = symbol;
        this.name = companyName;
        this.price = finalPrice;
        this.lastUpdated = now;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
