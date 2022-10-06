package com.itexperts.bank.model;

import java.util.List;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "Type_Card")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "typeCard")
	private List<Card> cards;
	
	
	public TypeCard(String name) {
		this.name = name;
	}
}
