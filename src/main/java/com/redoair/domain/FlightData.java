package com.redoair.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class FlightData {

	@Id @GeneratedValue
	private Long id;
	
	@OneToOne
	@JoinColumn(name="economyClassId")
	private EconomyClassData economyClassData;
	
	@OneToOne
	@JoinColumn(name="businessClassId")
	private BusinessClassData businessClassData;
	
	@OneToOne
	@JoinColumn(name="firstClassId")
	private FirstClassData firstClassData;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EconomyClassData getEconomyClassData() {
		return economyClassData;
	}

	public void setEconomyClassData(EconomyClassData economyClassData) {
		this.economyClassData = economyClassData;
	}

	public BusinessClassData getBusinessClassData() {
		return businessClassData;
	}

	public void setBusinessClassData(BusinessClassData businessClassData) {
		this.businessClassData = businessClassData;
	}

	public FirstClassData getFirstClassData() {
		return firstClassData;
	}

	public void setFirstClassData(FirstClassData firstClassData) {
		this.firstClassData = firstClassData;
	}
	
	
}
