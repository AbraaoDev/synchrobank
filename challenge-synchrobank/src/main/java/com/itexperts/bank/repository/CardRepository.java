package com.itexperts.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itexperts.bank.model.Account;
import com.itexperts.bank.model.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer>{

	Optional<Card> findByIdAndAccountId(Integer id, Account accountId);

	Optional<Card> findByNumber(String number);

}
