package com.stockportfolio.manager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "portfolios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    @Column(nullable = false)
    private Integer quantity; //Number of shares owned

    @Column(nullable = false)
    private Double purchasePrice;

    public User getUser() {
        return user;
    }

    public Stock getStock() {
        return stock;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}
