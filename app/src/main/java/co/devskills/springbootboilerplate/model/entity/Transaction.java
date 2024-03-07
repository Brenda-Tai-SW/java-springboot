package co.devskills.springbootboilerplate.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Table;


import java.time.LocalDateTime;

 @Entity
 @Table(name = "Transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "transactionId")
    private UUID transactionId;
    
    @Column(name = "accountId")
    private UUID accountId;
    
    @Column(name = "amount")
    private int amount;
    
    @Column(name = "createdAt")
    private LocalDateTime createdAt;
    
    @Column(name = "transactionHistory")
    private String transactionHistory;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long Id) {
        this.id = id;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public String getTransactionHistory() {
        return transactionHistory;
    }
    
    public void setTransactionHistory(String transactionHistory) {
        this.transactionHistory = transactionHistory;
    }
    
    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

   
}
