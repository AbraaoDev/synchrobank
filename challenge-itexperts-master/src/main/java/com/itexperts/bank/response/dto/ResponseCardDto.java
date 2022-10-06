package com.itexperts.bank.response.dto;

import com.itexperts.bank.enums.Flag;
import com.itexperts.bank.model.Card;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCardDto {

	private Integer id;
	private String name;
	private Flag flag;
	private String number;
	private String digitCode;
	private Double limitBalance;
	private ResponseTypeCardDto typeCard;
	
	public ResponseCardDto(Card card) {
		this.id = card.getId();
		this.name = card.getName();
		this.flag = card.getFlag();
		this.number = card.getNumber();
		this.digitCode = card.getDigitCode();
		this.limitBalance = card.getLimitBalance();
		this.typeCard = new ResponseTypeCardDto(card.getTypeCard());
	}
}
