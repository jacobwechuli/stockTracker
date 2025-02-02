package com.stockportfolio.manager.repository;

import com.stockportfolio.manager.model.Portfolio;
import com.stockportfolio.manager.model.Stock;
import com.stockportfolio.manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {
    List<Portfolio> findByUser(User user);

    Optional<Portfolio> findByUserAndStock(User user, Stock stock);
}
