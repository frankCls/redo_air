package com.redoair.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.redoair.domain.Pricing;

public abstract class abstractTravelingClassData {
	
	@NotNull
	@Embedded
	private Pricing pricing;
	
	@NotNull
	@Min(1)
	@Column(nullable=false)
	private int remainingSeats;

	public Pricing getPricing() {
		return pricing;
	}

	public void setPricing(Pricing pricing) {
		this.pricing = pricing;
	}

	public int getRemainingSeats() {
		return remainingSeats;
	}

	public void setRemainingSeats(int remainingSeats) {
		this.remainingSeats = remainingSeats;
	}
	
	
}
