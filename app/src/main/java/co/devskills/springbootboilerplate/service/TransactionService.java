package co.devskills.springbootboilerplate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import co.devskills.springbootboilerplate.repository.AccountRepository;
import co.devskills.springbootboilerplate.repository.TransactionRepository;
import co.devskills.springbootboilerplate.model.entity.Account;
import co.devskills.springbootboilerplate.model.entity.Transaction;
import co.devskills.springbootboilerplate.model.dto.TransactionRequest;
import co.devskills.springbootboilerplate.model.dto.TransactionsWithBalance;
import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import co.devskills.springbootboilerplate.service.TransactionCreationException;


@Service
public class TransactionService {
	
	private static final Logger log = LoggerFactory.getLogger(TransactionService.class);


    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public TransactionsWithBalance createTransaction(TransactionRequest transactionRequest){
        try {
      
        	 // create a new transacton
            Transaction transaction = buildTransactionFromRequest(transactionRequest);
            
            Transaction savedTransaction = transactionRepository.save(transaction);
           // create a new account or update the exist account
            Account account = updateAccountBalance(savedTransaction);
            
            // Fetch all  the transactions with the same account
            List<Transaction> transactions = transactionRepository.findByAccountId(transaction.getAccountId());
            
            // Get all the transactions history for the same account and the current balance for the last submitted transaction
            TransactionsWithBalance transactionsWithBalance = new TransactionsWithBalance();
           
            transactionsWithBalance.setTransactions(transactions);
            transactionsWithBalance.setAccountBalance(account.getBalance());
            
           return transactionsWithBalance; 
            
        } catch (IllegalArgumentException ex) {
            log.error("Invalid input: {}", ex.getMessage());
            throw new IllegalArgumentException("Invalid input: " + ex.getMessage());
        } catch (Exception ex) {
           
            log.error("Error creating transaction: {}", ex.getMessage());
            throw new TransactionCreationException("Failed to create transaction", ex);
        }
        
    }

    
    
    private Transaction buildTransactionFromRequest(TransactionRequest transactionRequest) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(UUID.randomUUID());
        transaction.setAccountId(transactionRequest.getAccountId());
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setCreatedAt(LocalDateTime.now());
        
        StringBuilder historyBuilder = new StringBuilder();
        historyBuilder.append("Transferred ");
        historyBuilder.append(transaction.getAmount());
        if (transaction.getAmount() > 0) {
            historyBuilder.append(" to ");
        } else {
            historyBuilder.append(" from ");
        }
        historyBuilder.append(" Account ID: ").append(transaction.getAccountId());
        transaction.setTransactionHistory(historyBuilder.toString());
        
        return transaction;
    }

    
    private Account updateAccountBalance(Transaction transaction) {
        // Fetch the account associated with the transaction
        Account account = accountRepository.findByAccountId(transaction.getAccountId());

        if (account == null) {
            // If the account doesn't exist, create a new one with the initial balance
            account = createNewAccount(transaction);
        } else {
            // Update the account balance based on the amount value.
            if (transaction.getAmount() > 0) {
                // Deposit: Add transaction amount to account balance
                account.setBalance(account.getBalance() + transaction.getAmount());
            } else {
                // Withdrawal: Subtract transaction amount from account balance
                account.setBalance(account.getBalance() - Math.abs(transaction.getAmount()));
            }
            // Save the updated account
            account = accountRepository.save(account);
        }

        return account;
    }

    private Account createNewAccount(Transaction transaction) {
        Account account = new Account();
        account.setAccountId(transaction.getAccountId());
        account.setBalance(transaction.getAmount());
        // Save the new account
        return accountRepository.save(account);
    }


   
    
}
