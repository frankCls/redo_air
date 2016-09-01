package com.redoair.domain;

import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;

@Entity
public class Pricing {

	@Id @GeneratedValue
	private Long id;
	
	private double defaultPrice;

	private double basePrice;

	@ElementCollection
	@CollectionTable(name="discounts")
	@MapKeyColumn(name="seatTreshold")
	@Column(name="discountPercentage")
	private Map<Integer,Double> discounts;

	public double getDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(double defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public Map<Integer, Double> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(Map<Integer, Double> discounts) {
		this.discounts = discounts;
	}

	

}
