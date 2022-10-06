package com.itexperts.bank.model;

import java.util.List;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "Account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name_owner")
	private String nameOwner;
	
	@Column(name = "agency_code")
	private String agencyCode;
	
	@Column(name = "account_code")
	private String accountCode;
	
	@Column(name = "digit_verification")
	private String digitVerification;
	
	@Column(name = "register_id")
	private String registerId;
	
	@OneToMany(mappedBy = "accountId")
	private List<Card> cards;


	public Account(String nameOwner, String agencyCode, String accountCode, String digitVerification, String registerId, List<Card> cards) {
		this.nameOwner = nameOwner;
		this.agencyCode = agencyCode;
		this.accountCode = accountCode;
		this.digitVerification = digitVerification;
		this.registerId = registerId;
		this.cards = cards;
	}

}
