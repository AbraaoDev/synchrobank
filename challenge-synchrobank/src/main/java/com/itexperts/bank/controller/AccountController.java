package com.itexperts.bank.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.itexperts.bank.request.dto.RequestAccountDto;
import com.itexperts.bank.request.dto.RequestCardDto;
import com.itexperts.bank.response.dto.ResponseAccountDto;
import com.itexperts.bank.service.AccountService;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;


@RestController
@RequestMapping("/api/v1/accounts")
@CrossOrigin("*")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	
	@ApiOperation("busca todas as contas existentes e retorna o resultado paginado.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK" ),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@GetMapping
	public ResponseEntity<Page<ResponseAccountDto>> findAll(Pageable pageable){
		return ResponseEntity.ok().body(accountService.findAll(pageable));
	}
	
	
	@ApiOperation("busca uma conta existente, pelo Id informado.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK" ),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@GetMapping("/{id}")
	public ResponseEntity<ResponseAccountDto> findById(@PathVariable Integer id){
		return ResponseEntity.ok().body(accountService.findById(id));
	}
	
	
	@ApiOperation("cria uma nova conta.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created" ),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@PostMapping
	public ResponseEntity<Void> createAccount(@Valid @RequestBody RequestAccountDto account){
		ResponseAccountDto accountDto = accountService.createAccount(account);
		
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(accountDto.getId())
                .toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
	@ApiOperation("deleta uma conta existente.")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "No Content" ),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAccount(@PathVariable Integer id){
		accountService.deleteAccount(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@ApiOperation("cria um novo cartão para uma conta existente.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created" ),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@PostMapping("/{id}/cards")
	public ResponseEntity<Void> createCard(@Valid @RequestBody RequestCardDto card, @PathVariable Integer id){
		ResponseAccountDto accountDto = accountService.createCard(card, id);
		
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(accountDto.getId())
                .toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
	@ApiOperation("deleta um cartão de uma conta existente.")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "No Content" ),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@DeleteMapping("/{idAccount}/cards/{idCard}")
	public ResponseEntity<Void> deleteCard(@PathVariable Integer idAccount, @PathVariable Integer idCard){
		accountService.deleteCard(idAccount, idCard);
		
		return ResponseEntity.noContent().build();
	}
}
