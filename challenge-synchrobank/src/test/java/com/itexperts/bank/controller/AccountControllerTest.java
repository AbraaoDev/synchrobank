package com.itexperts.bank.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.itexperts.bank.controller.AccountController;
import com.itexperts.bank.enums.Flag;
import com.itexperts.bank.request.dto.RequestAccountDto;
import com.itexperts.bank.request.dto.RequestCardDto;
import com.itexperts.bank.request.dto.RequestTypeCardDto;
import com.itexperts.bank.response.dto.ResponseAccountDto;
import com.itexperts.bank.response.dto.ResponseCardDto;
import com.itexperts.bank.service.AccountService;


@WebMvcTest(controllers = AccountController.class)
public class AccountControllerTest {

	@MockBean
	AccountService accountService;
	
	@Autowired
	AccountController accountController;
	
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
	private final String NAME_TYPE_CARD = "Visa Gold";
	
	private ResponseAccountDto responseAccountDto;
	private RequestAccountDto requestAccountDto;
	private RequestCardDto requestCardDto;
	private Pageable pageable;
	
	
	@BeforeEach
	public void setup() {
		startResponseAccountDto();
		startRequestAccountDto();
		startRequestCardDto();
	}
	
	
	@Test
	public void whenFindAllShouldReturnResponseEntityOfPageOfResponseAccountDto() {
		when(accountService.findAll(pageable)).thenReturn(new PageImpl<>(List.of(responseAccountDto)));
		
		ResponseEntity<Page<ResponseAccountDto>> response = accountController.findAll(pageable);
		
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().getSize());
		
		assertEquals(ResponseAccountDto.class, response.getBody().getContent().get(0).getClass());
		assertEquals(ID, response.getBody().getContent().get(0).getId());
		assertEquals(NAME_OWNER, response.getBody().getContent().get(0).getNameOwner());
		assertEquals(AGENCY_CODE, response.getBody().getContent().get(0).getAgencyCode());
		assertEquals(ACCOUNT_CODE, response.getBody().getContent().get(0).getAccountCode());
		assertEquals(DIGIT_VERIFICATION, response.getBody().getContent().get(0).getDigitVerification());
		assertEquals(REGISTER_ID, response.getBody().getContent().get(0).getRegisterId());
		assertEquals(new ArrayList<ResponseCardDto>(), response.getBody().getContent().get(0).getCards());
	}
	
	
	@Test
	public void whenFindByIdShouldReturnResponseEntityOfResponseAccountDto() {
		when(accountService.findById(ID)).thenReturn(responseAccountDto);
		
		ResponseEntity<ResponseAccountDto> response = accountController.findById(ID);
		
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		assertEquals(ResponseAccountDto.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(NAME_OWNER, response.getBody().getNameOwner());
		assertEquals(AGENCY_CODE, response.getBody().getAgencyCode());
		assertEquals(ACCOUNT_CODE, response.getBody().getAccountCode());
		assertEquals(DIGIT_VERIFICATION, response.getBody().getDigitVerification());
		assertEquals(REGISTER_ID, response.getBody().getRegisterId());
		assertEquals(new ArrayList<ResponseCardDto>(), response.getBody().getCards());
	}
	
	
	@Test
	public void whenCreateAccountShouldReturnStatusCodeCreated() {
		when(accountService.createAccount(requestAccountDto)).thenReturn(responseAccountDto);
		
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
		
		ResponseEntity<Void> response = accountController.createAccount(requestAccountDto);
		
		assertFalse(response.hasBody());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getHeaders().get("location"));
	}
	
	
	@Test
	public void whenDeleteAccountShouldReturnStatusCodeNoContent() {
		ResponseEntity<Void> response = accountController.deleteAccount(ID);
		
		assertFalse(response.hasBody());
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	
	@Test
	public void whenCreateCardShouldReturnStatusCodeCreated() {
		when(accountService.createCard(requestCardDto, ID)).thenReturn(responseAccountDto);
		
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
		
		ResponseEntity<Void> response = accountController.createCard(requestCardDto, ID);
		
		assertFalse(response.hasBody());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getHeaders().get("location"));
	}
	
	
	@Test
	public void whenDeleteCardShouldReturnStatusCodeNoContent() {
		ResponseEntity<Void> response = accountController.deleteCard(ID, ID);
		
		assertFalse(response.hasBody());
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	
	public void startResponseAccountDto() {
		responseAccountDto = new ResponseAccountDto(ID, NAME_OWNER, AGENCY_CODE, ACCOUNT_CODE, DIGIT_VERIFICATION, REGISTER_ID, new ArrayList<ResponseCardDto>());
	}
	
	public void startRequestAccountDto() {
		requestAccountDto = new RequestAccountDto(NAME_OWNER, AGENCY_CODE, ACCOUNT_CODE, DIGIT_VERIFICATION, REGISTER_ID, new ArrayList<RequestCardDto>());
	}
	
	public void startRequestCardDto() {
		requestCardDto = new RequestCardDto(CARD_NAME, FLAG, NUMBER, DIGIT_CODE, LIMIT_BALANCE, new RequestTypeCardDto(NAME_TYPE_CARD));
	}
	
	
	
}
