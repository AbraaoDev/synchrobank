package com.itexperts.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itexperts.bank.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

	Optional<Account> findByRegisterId(String registerId);

}
