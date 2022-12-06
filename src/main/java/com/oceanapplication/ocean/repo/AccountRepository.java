package com.oceanapplication.ocean.repo;

import com.oceanapplication.ocean.models.Account;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account>  findByPhoneNumber(String phoneNumber);
}
