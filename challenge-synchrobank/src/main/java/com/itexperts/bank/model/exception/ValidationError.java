package com.itexperts.bank.model.exception;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationError {

	private String objectName;
	private String field;
	private String message;
	
}
	
