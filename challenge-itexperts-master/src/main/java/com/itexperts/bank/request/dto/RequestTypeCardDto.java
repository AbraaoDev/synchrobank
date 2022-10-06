package com.itexperts.bank.request.dto;

import javax.validation.constraints.*;

import com.itexperts.bank.model.TypeCard;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestTypeCardDto {

	@Size(min = 2, max = 50, message = "{typeCard.name.size}")
	@NotBlank(message = "{typeCard.name.notBlank}")
	private String name;
	
	public TypeCard dtoToEntity() {
		return new TypeCard(name);
	}
}
