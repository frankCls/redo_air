package com.redoair.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.redoair.domain.Pricing;

@Entity
@Table(name="travelingClassData")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.STRING)
public abstract class AbstractTravelingClassData {
	@Id @GeneratedValue
	private Long id;
	
	@NotNull
	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	@JoinColumn(name="priceId")
	private Pricing pricing;
	
	@NotNull
	@Min(0)
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
