package com.itexperts.bank.model;

import javax.persistence.*;

import com.itexperts.bank.enums.Flag;

import lombok.*;

@Entity
@Table (name = "Card")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "flag")
	@Enumerated(EnumType.STRING)
	private Flag flag;
	
	@Column(name = "number")
	private String number;
	
	@Column(name = "digit_code")
	private String digitCode;
	
	@Column(name = "limit_balance")
	private Double limitBalance;
	
	@ManyToOne
	@JoinColumn(name = "Account_id")
	private Account accountId;
	
	@ManyToOne
	@JoinColumn(name = "Type_Card_id")
	private TypeCard typeCard;

	public Card(String name, Flag flag, String number, String digitCode, Double limitBalance, TypeCard typeCard) {
		this.name = name;
		this.flag = flag;
		this.number = number;
		this.digitCode = digitCode;
		this.limitBalance = limitBalance;
		this.typeCard = typeCard;
	}
	
	
}
