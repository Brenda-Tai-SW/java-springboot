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

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public TransactionsWithBalance createTransaction(TransactionRequest transactionRequest) {
        // Save the transaction to the database
    	
    	Transaction transaction = new Transaction();
    	transaction.setTransactionId(UUID.randomUUID());
    	transaction.setAccountId(UUID.nameUUIDFromBytes(transactionRequest.getAccountId().getBytes()));
    	transaction.setAmount(transactionRequest.getAmount());
    	transaction.setCreatedAt(LocalDateTime.now());
    	
    	StringBuilder historyBuilder = new StringBuilder();
        historyBuilder.append("Transferred  ");
        historyBuilder.append(transaction.getAmount());
        if (transaction.getAmount() > 0) {
            historyBuilder.append("to");
        } else {
            historyBuilder.append("from");
        }
        historyBuilder.append("  Amount: ").append(transaction.getAccountId());
        transaction.setTransactionHistory(historyBuilder.toString());
        
        Transaction savedTransaction = transactionRepository.save(transaction);
        
        List<Transaction> transactions = transactionRepository.findByAccountId(transaction.getAccountId());
        

        // Update account balance based on transaction type (deposit or withdrawal)
        Account account = accountRepository.findByAccountId(transaction.getAccountId());
        Account accountLastTransaction=null;
        
        if (account != null) {
            if (transaction.getAmount() > 0) {
                // Deposit: Add transaction amount to account balance
                account.setBalance(account.getBalance() + transaction.getAmount());
            } else {
                // Withdrawal: Subtract transaction amount from account balance
                account.setBalance(account.getBalance() - Math.abs(transaction.getAmount()));
            }
            // Save updated account balance
            accountLastTransaction= accountRepository.save(account);
        }else {
        	 Account accountNew =new Account();
        	 accountNew.setAccountId(transaction.getAccountId());
        	 accountNew.setBalance(transaction.getAmount());
        	 accountLastTransaction = accountRepository.save(accountNew);
        }
        
        TransactionsWithBalance transactionsWithBalance = new TransactionsWithBalance();
        
        transactionsWithBalance.setTransactions(transactions);
        transactionsWithBalance.setAccountBalance(accountLastTransaction.getBalance());
        
       return transactionsWithBalance;
    }
    
    
   
    
    
}
