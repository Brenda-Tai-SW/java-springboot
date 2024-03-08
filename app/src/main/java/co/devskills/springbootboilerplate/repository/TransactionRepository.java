package co.devskills.springbootboilerplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import co.devskills.springbootboilerplate.model.entity.Transaction;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountId(UUID accountId);
	
    Transaction save(Transaction transaction);
    
}

