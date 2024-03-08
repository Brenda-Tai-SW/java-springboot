package co.devskills.springbootboilerplate.model.dto;



import java.util.List;
import co.devskills.springbootboilerplate.model.entity.Transaction;

public class TransactionsWithBalance {
    private List<Transaction> transactions;
    private int accountBalance;

   
    public List<Transaction> getTransactions() {
        return transactions;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }
}

