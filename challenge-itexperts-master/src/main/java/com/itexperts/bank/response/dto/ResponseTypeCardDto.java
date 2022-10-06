package com.itexperts.bank.response.dto;

import com.itexperts.bank.model.TypeCard;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTypeCardDto {

	private Integer id;
	private String name;
	
	public ResponseTypeCardDto(TypeCard typeCard) {
		this.id = typeCard.getId();
		this.name = typeCard.getName();
	}
}
