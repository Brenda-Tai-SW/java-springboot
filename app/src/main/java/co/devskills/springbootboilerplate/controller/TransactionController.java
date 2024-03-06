package co.devskills.springbootboilerplate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import co.devskills.springbootboilerplate.service.TransactionService;
import java.util.List;
import co.devskills.springbootboilerplate.model.entity.Transaction;
import co.devskills.springbootboilerplate.model.dto.TransactionRequest;
import co.devskills.springbootboilerplate.model.dto.TransactionsWithBalance;

@RestController
@RequestMapping("/transactions")
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public TransactionsWithBalance createTransaction(@RequestBody TransactionRequest transactionRequest) {
        return transactionService.createTransaction(transactionRequest);
    }

}
