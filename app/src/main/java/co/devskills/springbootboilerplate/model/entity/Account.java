package co.devskills.springbootboilerplate.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Table;


 @Entity
 @Table(name = "account") 
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "accountId")
    private UUID accountId;
    
    @Column(name = "balance")
    private int balance;

    
    
    public  Long  getId() {
    	return id;
    }
    
    public void setId(Long Id) {
        this.id = Id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    
    
    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }
    
  
}
