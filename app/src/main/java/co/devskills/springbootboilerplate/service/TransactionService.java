package co.devskills.springbootboilerplate.service;

import co.devskills.springbootboilerplate.model.dto.TransactionRequest;
import co.devskills.springbootboilerplate.model.dto.TransactionsWithBalance;


public interface TransactionService {
    TransactionsWithBalance createTransaction(TransactionRequest transactionRequest);
}

