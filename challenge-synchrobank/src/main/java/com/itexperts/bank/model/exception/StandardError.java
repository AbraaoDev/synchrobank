package com.itexperts.bank.model.exception;

import java.time.Instant;
import java.util.List;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardError {

	private Instant timestamp;
	private Integer code;
	private String status;
	private String message;
	private String path;
	private List<ValidationError> errors;
	
	public StandardError(Instant timestamp, Integer code, String status, String message, String path) {
		this.timestamp = timestamp;
		this.code = code;
		this.status = status;
		this.message = message;
		this.path = path;
	}
	
	
	
}
