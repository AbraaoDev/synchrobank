package com.itexperts.bank.response.dto;

import java.util.ArrayList;
import java.util.List;

import com.itexperts.bank.model.Account;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAccountDto {

	private Integer id;
	private String nameOwner;
	private String agencyCode;
	private String accountCode;
	private String digitVerification;
	private String registerId;
	private List<ResponseCardDto> cards = new ArrayList<>();
	
	public ResponseAccountDto(Account account) {
		this.id = account.getId();
		this.nameOwner = account.getNameOwner();
		this.agencyCode = account.getAgencyCode();
		this.accountCode = account.getAccountCode();
		this.digitVerification = account.getDigitVerification();
		this.registerId = account.getRegisterId();
		account.getCards().forEach(c -> cards.add(new ResponseCardDto(c)));
	}
}
