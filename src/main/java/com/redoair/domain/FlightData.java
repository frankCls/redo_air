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
	
	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE})
	@JoinColumn(name="economyClassId")
	private AbstractTravelingClassData economyClass;
	
	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE})
	@JoinColumn(name="businnessClassId")
	private AbstractTravelingClassData businnessClass;
	
	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE})
	@JoinColumn(name="firstClassId")
	private AbstractTravelingClassData firstClass;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((businnessClass == null) ? 0 : businnessClass.hashCode());
		result = prime * result + ((economyClass == null) ? 0 : economyClass.hashCode());
		result = prime * result + ((firstClass == null) ? 0 : firstClass.hashCode());
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
		FlightData other = (FlightData) obj;
		if (businnessClass == null) {
			if (other.businnessClass != null)
				return false;
		} else if (!businnessClass.equals(other.businnessClass))
			return false;
		if (economyClass == null) {
			if (other.economyClass != null)
				return false;
		} else if (!economyClass.equals(other.economyClass))
			return false;
		if (firstClass == null) {
			if (other.firstClass != null)
				return false;
		} else if (!firstClass.equals(other.firstClass))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
}
