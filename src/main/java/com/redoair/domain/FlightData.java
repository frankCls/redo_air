package com.redoair.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "flightData")
public class FlightData {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="economyClassId")
	private AbstractTravelingClassData businnessClass;
	
	@OneToOne
	@JoinColumn(name="businnessClassId")
	private AbstractTravelingClassData economyClass;
	
	@OneToOne
	@JoinColumn(name="firstClassId")
	private AbstractTravelingClassData firstClass;

	public AbstractTravelingClassData getBusinnessClass() {
		return businnessClass;
	}

	public void setBusinnessClass(AbstractTravelingClassData businnessClass) {
		this.businnessClass = businnessClass;
	}

	public AbstractTravelingClassData getEconomyClass() {
		return economyClass;
	}

	public void setEconomyClass(AbstractTravelingClassData economyClass) {
		this.economyClass = economyClass;
	}

	public AbstractTravelingClassData getFirstClass() {
		return firstClass;
	}

	public void setFirstClass(AbstractTravelingClassData firstClass) {
		this.firstClass = firstClass;
	}

	
}
