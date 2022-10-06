package com.itexperts.bank.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.itexperts.bank.enums.Flag;
import com.itexperts.bank.model.Account;
import com.itexperts.bank.model.Card;
import com.itexperts.bank.model.TypeCard;
import com.itexperts.bank.repository.AccountRepository;
import com.itexperts.bank.repository.CardRepository;
import com.itexperts.bank.request.dto.RequestAccountDto;
import com.itexperts.bank.request.dto.RequestCardDto;
import com.itexperts.bank.request.dto.RequestTypeCardDto;
import com.itexperts.bank.response.dto.ResponseAccountDto;
import com.itexperts.bank.service.AccountService;
import com.itexperts.bank.service.exception.AccountActiveCardsException;
import com.itexperts.bank.service.exception.AccountNotFoundException;
import com.itexperts.bank.service.exception.CardNotFoundException;
import com.itexperts.bank.service.exception.ExistingAccountException;
import com.itexperts.bank.service.exception.ExistingCardException;

@SpringBootTest
public class AccountServiceTest {

	@MockBean
	AccountRepository accountRepository;
	
	@MockBean
	CardRepository cardRepository;
	
	@Autowired
	AccountService accountService;
	
	private final Integer ID = 1;
	private final String NAME_OWNER = "Francisco";
	private final String AGENCY_CODE = "3421";
	private final String ACCOUNT_CODE = "85742586";
	private final String DIGIT_VERIFICATION = "2";
	private final String REGISTER_ID = "45724587451";
	
	private final String CARD_NAME = "Visa Gold";
	private final Flag FLAG = Flag.VISA;
	private final String NUMBER = "4587254875481254";
	private final String DIGIT_CODE = "458";
	private final Double LIMIT_BALANCE = 200.00;
	private final Account ACCOUNT_ID = new Account(ID, NAME_OWNER, AGENCY_CODE, ACCOUNT_CODE, DIGIT_VERIFICATION, REGISTER_ID, new ArrayList<Card>());
	private final String NAME_TYPE_CARD = "Visa Gold";
	
	private final Integer INVALID_ID = 1000;
	private final String ACCOUNT_NOT_FOUND_MESSAGE = "Não foi possível encontrar uma conta com o id informado.";
	private final String EXISTING_ACCOUNT_MESSAGE = "Não foi possível criar uma conta. Já existe uma conta com o 'registerId' informado.";
	private final String EXISTING_CARD_MESSAGE = "Não foi possível criar um cartão. Já existe um cartão com o 'number' informado.";
	private final String ACCOUNT_ACTIVE_CARDS_MESSAGE = "Não foi possível deletar a conta. A conta informada possui cartões ativos.";
	private final String CARD_NOT_FOUND_MESSAGE = "Não foi possível encontrar um cartão com o id informado.";
	
	private Card card;
	private Account account;
	private RequestCardDto requestCardDto;
	private RequestAccountDto requestAccountDto;
	private Account accountWithCards;
	
	private Pageable pageable;
	
	@BeforeEach
	public void setup() {
		startAccount();
		startCard();
	}
	
	
	@Test
	public void whenFindAllShouldReturnPageOfResponseAccountDto() {
		when(accountRepository.findAll(pageable)).thenReturn(new PageImpl<>(List.of(account, account, account)));
		
		Page<ResponseAccountDto> pgAccount = accountService.findAll(pageable);
		
		assertNotNull(pgAccount);
		assertEquals(3, pgAccount.getSize());
		assertEquals(ResponseAccountDto.class, pgAccount.getContent().get(0).getClass());
		assertEquals(account.getId(), pgAccount.getContent().get(0).getId());
		assertEquals(account.getNameOwner(), pgAccount.getContent().get(0).getNameOwner());
		assertEquals(account.getAgencyCode(), pgAccount.getContent().get(0).getAgencyCode());
		assertEquals(account.getAccountCode(), pgAccount.getContent().get(0).getAccountCode());
		assertEquals(account.getDigitVerification(), pgAccount.getContent().get(0).getDigitVerification());
		assertEquals(account.getRegisterId(), pgAccount.getContent().get(0).getRegisterId());
		assertEquals(account.getCards(), pgAccount.getContent().get(0).getCards());
		
	}
	
	
	@Test
	public void whenFindByIdShouldReturnResponseAccountDto() {
		when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
		
		ResponseAccountDto responseAccountDto = accountService.findById(1);
		
		assertNotNull(responseAccountDto);
		assertEquals(account.getId(), responseAccountDto.getId());
		assertEquals(account.getNameOwner(), responseAccountDto.getNameOwner());
		assertEquals(account.getAgencyCode(), responseAccountDto.getAgencyCode());
		assertEquals(account.getAccountCode(), responseAccountDto.getAccountCode());
		assertEquals(account.getDigitVerification(), responseAccountDto.getDigitVerification());
		assertEquals(account.getRegisterId(), responseAccountDto.getRegisterId());
		assertEquals(account.getCards(), responseAccountDto.getCards());
	}
	
	
	@Test
	public void whenFindByIdShouldThrowAccountNotFoundException() {
		when(accountRepository.findById(INVALID_ID)).thenReturn(Optional.empty());
	
		try {
			accountRepository.findById(INVALID_ID);
			
		}catch(AccountNotFoundException ex) {
			assertEquals(AccountNotFoundException.class, ex.getClass());
			assertEquals(ACCOUNT_NOT_FOUND_MESSAGE, ex.getMessage());
		}
	}
	
	
	@Test
	public void whenCreateAccountShouldReturnResponseAccountDto() {
		when(accountRepository.save(account)).thenReturn(account);
		
		ResponseAccountDto responseAccountDto = accountService.createAccount(requestAccountDto);
		
		assertNotNull(responseAccountDto);
		assertEquals(NAME_OWNER, responseAccountDto.getNameOwner());
		assertEquals(AGENCY_CODE, responseAccountDto.getAgencyCode());
		assertEquals(ACCOUNT_CODE, responseAccountDto.getAccountCode());
		assertEquals(DIGIT_VERIFICATION, responseAccountDto.getDigitVerification());
		assertEquals(REGISTER_ID, responseAccountDto.getRegisterId());
	
	}
	
	
	@Test
	public void whenCreateAccountShouldThrowExistingAccountException() {
		when(accountRepository.findByRegisterId(REGISTER_ID)).thenReturn(Optional.of(account));
		
		try {
			accountService.createAccount(requestAccountDto);
			
		}catch(ExistingAccountException ex) {
			assertEquals(ExistingAccountException.class, ex.getClass());
			assertEquals(EXISTING_ACCOUNT_MESSAGE, ex.getMessage());
		}
	}
	
	
	@Test
	public void whenCreateAccountShouldThrowExistingCardException() {
		when(cardRepository.findByNumber(NUMBER)).thenReturn(Optional.of(card));
		
		try {
			accountService.createAccount(requestAccountDto);
			
		}catch(ExistingCardException ex) {
			assertEquals(ExistingCardException.class, ex.getClass());
			assertEquals(EXISTING_CARD_MESSAGE, ex.getMessage());
		}
	}
	
	
	@Test
	public void whenDeleteAccountShouldReturnNothing() {
		when(accountRepository.findById(ID)).thenReturn(Optional.of(account));
		
		accountService.deleteAccount(ID);
	}
	
	
	@Test
	public void whenDeleteAccountShouldThrowAccountNotFoundException() {
		when(accountRepository.findById(ID)).thenReturn(Optional.empty());
		
		try {
			accountService.deleteAccount(ID);
			
		}catch(AccountNotFoundException ex) {
			assertEquals(AccountNotFoundException.class, ex.getClass());
			assertEquals(ACCOUNT_NOT_FOUND_MESSAGE, ex.getMessage());
		}
	}
	
	
	@Test
	public void whenDeleteAccountShouldThrowAccountActiveCardsException() {
		when(accountRepository.findById(ID)).thenReturn(Optional.of(accountWithCards));
		
		try {
			accountService.deleteAccount(ID);
			
		}catch(AccountActiveCardsException ex) {
			assertEquals(AccountActiveCardsException.class, ex.getClass());
			assertEquals(ACCOUNT_ACTIVE_CARDS_MESSAGE, ex.getMessage());
		
		}
	}
	
	
	@Test
	public void whenCreateCardShouldReturnResponseAccountDto() {
		when(accountRepository.findById(ID)).thenReturn(Optional.of(account));
		when(cardRepository.findByNumber(NUMBER)).thenReturn(Optional.empty());
		
		ResponseAccountDto responseAccountDto = accountService.createCard(requestCardDto, ID);
		
		assertNotNull(responseAccountDto);
		assertEquals(ID, responseAccountDto.getId());
	}
	
	
	@Test
	public void whenCreateCardShouldThrowAccountNotFoundException() {
		when(accountRepository.findById(ID)).thenReturn(Optional.empty());
		
		try {
			accountService.createCard(requestCardDto, ID);
			
		}catch(AccountNotFoundException ex) {
			assertEquals(AccountNotFoundException.class, ex.getClass());
			assertEquals(ACCOUNT_NOT_FOUND_MESSAGE, ex.getMessage());
		}
	}
	
	
	@Test
	public void whenCreateCardShouldThrowExistingCardException() {
		when(accountRepository.findById(ID)).thenReturn(Optional.of(account));
		when(cardRepository.findByNumber(NUMBER)).thenReturn(Optional.of(card));
		
		try {
			accountService.createCard(requestCardDto, ID);
			
		}catch(ExistingCardException ex) {
			assertEquals(ExistingCardException.class, ex.getClass());
			assertEquals(EXISTING_CARD_MESSAGE, ex.getMessage());
		}
	}
	
	
	@Test
	public void whenDeleteCardShouldReturnNothing() {
		when(accountRepository.findById(ID)).thenReturn(Optional.of(account));
		when(cardRepository.findByIdAndAccountId(ID, ACCOUNT_ID)).thenReturn(Optional.of(card));
		
		accountService.deleteCard(ID, ID);
	}
	
	
	@Test
	public void whenDeleteCardShouldThrowAccountNotFoundException() {
		when(accountRepository.findById(ID)).thenReturn(Optional.empty());
		
		try {
			accountService.deleteCard(ID, ID);
			
		}catch(AccountNotFoundException ex) {
			assertEquals(AccountNotFoundException.class, ex.getClass());
			assertEquals(ACCOUNT_NOT_FOUND_MESSAGE, ex.getMessage());
		}
	}
	
	
	@Test
	public void whenDeleteCardShouldThrowCardNotFoundException() {
		when(accountRepository.findById(ID)).thenReturn(Optional.of(account));
		when(cardRepository.findByIdAndAccountId(ID, ACCOUNT_ID)).thenReturn(Optional.empty());
		
		try {
			accountService.deleteCard(ID, ID);
			
		}catch(CardNotFoundException ex) {
			assertEquals(CardNotFoundException.class, ex.getClass());
			assertEquals(CARD_NOT_FOUND_MESSAGE, ex.getMessage());
		}
	}
	
	
	public void startAccount() {
		account = new Account(ID, NAME_OWNER, AGENCY_CODE, ACCOUNT_CODE, DIGIT_VERIFICATION, REGISTER_ID, new ArrayList<Card>());
		
		accountWithCards = new Account(ID, NAME_OWNER, AGENCY_CODE, ACCOUNT_CODE, DIGIT_VERIFICATION, REGISTER_ID, 
				List.of(new Card(ID, CARD_NAME, FLAG, NUMBER, DIGIT_CODE, LIMIT_BALANCE, ACCOUNT_ID, new TypeCard(NAME_TYPE_CARD))));
		
		requestAccountDto = new RequestAccountDto(NAME_OWNER, AGENCY_CODE, ACCOUNT_CODE, DIGIT_VERIFICATION, REGISTER_ID, 
				List.of(new RequestCardDto(CARD_NAME, FLAG, NUMBER, DIGIT_CODE, LIMIT_BALANCE, new RequestTypeCardDto(NAME_TYPE_CARD))));
	}
	
	public void startCard() {
		card = new Card(ID, CARD_NAME, FLAG, NUMBER, DIGIT_CODE, LIMIT_BALANCE, ACCOUNT_ID, new TypeCard());
		requestCardDto = new RequestCardDto(CARD_NAME, FLAG, NUMBER, DIGIT_CODE, LIMIT_BALANCE, new RequestTypeCardDto(NAME_TYPE_CARD));
	}
	
	
	

}
