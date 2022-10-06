package com.itexperts.bank.request.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.*;


import com.itexperts.bank.model.Account;
import com.itexperts.bank.model.Card;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAccountDto {

	@Size(min = 2, max = 50, message = "{account.nameOwner.size}")
	@NotBlank(message = "{account.nameOwner.notBlank}")
	private String nameOwner;
	
	
	@Digits(integer = 4, fraction = 0, message = "{account.agencyCode.digits}")
	@Size(min = 4, max = 4, message = "{account.agencyCode.size}")
	@NotBlank(message = "{account.agencyCode.notBlank}")
	private String agencyCode;
	
	
	@Digits(integer = 8, fraction = 0, message = "{account.accountCode.digits}")
	@Size(min = 8, max = 8, message = "{account.accountCode.size}")
	@NotBlank(message = "{account.accountCode.notBlank}")
	private String accountCode;
	
	
	@Digits(integer = 1, fraction = 0, message = "{account.digitVerification.digits}")
	@Size(max = 1, message = "{account.digitVerification.size}")
	@NotBlank(message = "{account.digitVerification.notBlank}")
	private String digitVerification;
	
	
	@Digits(integer = 11, fraction = 0, message = "{account.registerId.digits}")
	@Size(min = 11, max = 11, message = "{account.registerId.size}")
	@NotBlank(message = "{account.registerId.notBlank}")
	private String registerId;
	
	@Valid
	@NotEmpty(message = "{account.cards.notEmpty}")
	private List<RequestCardDto> cards;

	
	public Account dtoToEntity() {
		List<Card> cards = new ArrayList<>();
		
		this.cards.forEach(cardDto -> {
			cards.add(cardDto.dtoToEntity());
		});
		
		return new Account(nameOwner, agencyCode, accountCode, digitVerification, registerId, cards);
	}
}
