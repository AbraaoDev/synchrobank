package com.itexperts.bank.controller.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import com.itexperts.bank.controller.exception.ControllerExceptionHandler;
import com.itexperts.bank.model.exception.StandardError;
import com.itexperts.bank.service.exception.AccountActiveCardsException;
import com.itexperts.bank.service.exception.AccountNotFoundException;
import com.itexperts.bank.service.exception.CardNotFoundException;
import com.itexperts.bank.service.exception.ExistingAccountException;
import com.itexperts.bank.service.exception.ExistingCardException;

@SpringBootTest
public class ControllerExceptionHandlerTest {

	@InjectMocks
	ControllerExceptionHandler controllerExceptionHandler;
	
	private final String ACCOUNT_NOT_FOUND_MESSAGE = "Não foi possível encontrar uma conta com o id informado.";
	private final String EXISTING_ACCOUNT_MESSAGE = "Não foi possível criar uma conta. Já existe uma conta com o 'registerId' informado.";
	private final String EXISTING_CARD_MESSAGE = "Não foi possível criar um cartão. Já existe um cartão com o 'number' informado.";
	private final String ACCOUNT_ACTIVE_CARDS_MESSAGE = "Não foi possível deletar a conta. A conta informada possui cartões ativos.";
	private final String CARD_NOT_FOUND_MESSAGE = "Não foi possível encontrar um cartão com o id informado.";
	
	
	@Test
	public void whenAccountNotFoundExceptionShouldReturnResponseEntityWithAccountNotFoundMessage() {
		ResponseEntity<StandardError> response = controllerExceptionHandler.accountNotFound(
				new AccountNotFoundException(ACCOUNT_NOT_FOUND_MESSAGE), 
				new MockHttpServletRequest());
		
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode().value());
		assertEquals(ACCOUNT_NOT_FOUND_MESSAGE, response.getBody().getMessage());
		
	}
	
	
	@Test
	public void whenCardNotFoundExceptionShouldReturnResponseEntityWithCardNotFoundMessage() {
		ResponseEntity<StandardError> response = controllerExceptionHandler.cardNotFound(
				new CardNotFoundException(CARD_NOT_FOUND_MESSAGE), 
				new MockHttpServletRequest());
		
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode().value());
		assertEquals(CARD_NOT_FOUND_MESSAGE, response.getBody().getMessage());
		
	}
	
	
	@Test
	public void whenExistingAccountExceptionShouldReturnResponseEntityWithExistingAccountMessage() {
		ResponseEntity<StandardError> response = controllerExceptionHandler.existingAccount(
				new ExistingAccountException(EXISTING_ACCOUNT_MESSAGE), 
				new MockHttpServletRequest());
		
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
		assertEquals(EXISTING_ACCOUNT_MESSAGE, response.getBody().getMessage());
		
	}
	
	
	@Test
	public void whenExistingCardExceptionShouldReturnResponseEntityWithExistingCardMessage() {
		ResponseEntity<StandardError> response = controllerExceptionHandler.existingCard(
				new ExistingCardException(EXISTING_CARD_MESSAGE), 
				new MockHttpServletRequest());
		
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
		assertEquals(EXISTING_CARD_MESSAGE, response.getBody().getMessage());
		
	}
	
	
	@Test
	public void whenAccountActiveCardsExceptionShouldReturnResponseEntityWithAccountActiveCardsMessage() {
		ResponseEntity<StandardError> response = controllerExceptionHandler.accountActiveCards(
				new AccountActiveCardsException(ACCOUNT_ACTIVE_CARDS_MESSAGE), 
				new MockHttpServletRequest());
		
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
		assertEquals(ACCOUNT_ACTIVE_CARDS_MESSAGE, response.getBody().getMessage());
	}
	
}
