package com.itexperts.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itexperts.bank.model.TypeCard;

@Repository
public interface TypeCardRepository extends JpaRepository<TypeCard, Integer>{

	Optional<TypeCard> findByNameIgnoreCase(String name);
}
