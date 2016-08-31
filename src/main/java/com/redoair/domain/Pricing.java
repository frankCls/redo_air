package com.redoair.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Pricing {
	
	private double defaultPrice;
	
	private double basePrice;
	
	private double discount;
}
