package com.stockportfolio.manager.repository;

import com.stockportfolio.manager.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    Optional<Stock> findBySymbol(String symbol);
}
