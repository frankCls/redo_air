package com.redoair.domain;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;

@Entity
public class Pricing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private double defaultPrice;

	private double basePrice;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "discounts")
	@MapKeyColumn(name = "seatTreshold")
	@Column(name = "discountPercentage")
	private Map<Integer, Double> discounts = new HashMap<>();

	private Double calculatedPrice;

	public Pricing() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Pricing(double defaultPrice, double basePrice, Map<Integer, Double> discounts, Double calculatedPrice) {
		super();
		this.defaultPrice = defaultPrice;
		this.basePrice = basePrice;
		this.discounts = discounts;
		this.calculatedPrice = calculatedPrice;
	}



	public double getDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(double defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(basePrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(defaultPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((discounts == null) ? 0 : discounts.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pricing other = (Pricing) obj;
		if (Double.doubleToLongBits(basePrice) != Double.doubleToLongBits(other.basePrice))
			return false;
		if (Double.doubleToLongBits(defaultPrice) != Double.doubleToLongBits(other.defaultPrice))
			return false;
		if (discounts == null) {
			if (other.discounts != null)
				return false;
		} else if (!discounts.equals(other.discounts))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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

	public Double getCalculatedPrice() {
		return calculatedPrice;
	}

	public void setCalculatedPrice(Double calculatedPrice) {
		this.calculatedPrice = calculatedPrice;
	}

}
