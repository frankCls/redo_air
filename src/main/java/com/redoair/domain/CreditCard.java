package com.redoair.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.redoair.domain.CreditCardType;

@Embeddable
public class CreditCard implements Serializable {

	
	
	@Enumerated
	private CreditCardType typeCreditCard;



	

	public CreditCardType getTypeCreditCard() {
		return typeCreditCard;
	}

	public void setTypeCreditCard(CreditCardType typeCreditCard) {
		this.typeCreditCard = typeCreditCard;
	}
	
	
}
