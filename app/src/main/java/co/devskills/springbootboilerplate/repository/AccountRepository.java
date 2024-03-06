package co.devskills.springbootboilerplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import co.devskills.springbootboilerplate.model.entity.Account;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, Long> {
     Account findByAccountId(UUID accountId);
	
     Account save(Account account);
}


