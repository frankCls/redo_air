package com.redoair.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.redoair.domain.CreditCardType;

@Embeddable
public class CreditCard implements Serializable {

/*	@ManyToOne
	private Payer payer;*/
	
	@Enumerated(EnumType.STRING)
	private CreditCardType typeCreditCard;

	private long creditCardNumber;

	/*public Payer getPayer_id() {
		return payer;
	}

	public void setPayer_id(Payer payer) {
		this.payer = payer;
	}*/

	public CreditCardType getTypeCreditCard() {
		return typeCreditCard;
	}

	public void setTypeCreditCard(CreditCardType typeCreditCard) {
		this.typeCreditCard = typeCreditCard;
	}

	public long getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(long creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	
	
}
