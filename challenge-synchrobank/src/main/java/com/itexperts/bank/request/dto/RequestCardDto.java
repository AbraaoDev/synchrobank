package com.itexperts.bank.request.dto;

import javax.validation.Valid;
import javax.validation.constraints.*;

import com.itexperts.bank.enums.Flag;
import com.itexperts.bank.model.Card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCardDto {

	@Size(min = 2, max = 50, message = "{card.name.size}")
	@NotBlank(message = "{card.name.notBlank}")
	private String name;

	private Flag flag;
	
	@Digits(integer = 16, fraction = 0, message = "{card.number.digits}")
	@Size(min = 16, max = 16, message = "{card.number.size}")
	@NotBlank(message = "{card.number.notBlank}")
	private String number;
	
	@Digits(integer = 5, fraction = 0, message = "{card.digitCode.digits}")
	@Size(min = 3, max = 5, message = "{card.digitCode.size}")
	@NotBlank(message = "{card.digitCode.notBlank}")
	private String digitCode;
	
	@Digits(integer = 14, fraction = 2, message = "{card.limitBalance.digits}")
	@NotNull(message = "{card.limitBalance.notNull}")
	private Double limitBalance;
	
	@Valid
	@NotNull(message = "{card.typeCard.notNull}")
	private RequestTypeCardDto typeCard;
	
	
	public Card dtoToEntity() {	
		return new Card(name, flag, number, digitCode, limitBalance, typeCard.dtoToEntity());
	}
}
