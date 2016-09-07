package com.redoair.domain;



import javax.persistence.CascadeType;
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
	
	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	@JoinColumn(name="economyClassId")
	private BusinessClassData businnessClass;
	
	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	@JoinColumn(name="businnessClassId")
	private EconomyClassData economyClass;
	
	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	@JoinColumn(name="firstClassId")
	private FirstClassData firstClass;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AbstractTravelingClassData getBusinnessClass() {
		return businnessClass;
	}

	public void setBusinnessClass(BusinessClassData businnessClass) {
		this.businnessClass = businnessClass;
	}

	public AbstractTravelingClassData getEconomyClass() {
		return economyClass;
	}

	public void setEconomyClass(EconomyClassData economyClass) {
		this.economyClass = economyClass;
	}

	public AbstractTravelingClassData getFirstClass() {
		return firstClass;
	}

	public void setFirstClass(FirstClassData firstClass) {
		this.firstClass = firstClass;
	}


	
}
