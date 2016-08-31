
package com.redoair.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.redoair.domain.PurchaseStatus;
import com.redoair.domain.TravelingClassType;
import com.sun.istack.NotNull;

@Entity
public class Ticket implements Serializable{
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Enumerated
	private TravelingClassType travelingClass;

	@Enumerated
	private PurchaseStatus purchaseStatus;
	
	@NotNull
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private Flight flight;
	
	@NotNull
	@OneToOne
	private Passenger passenger;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TravelingClassType getTravelingClass() {
		return travelingClass;
	}

	public void setTravelingClass(TravelingClassType travelingClass) {
		this.travelingClass = travelingClass;
	}

	public PurchaseStatus getPurchaseStatus() {
		return purchaseStatus;
	}

	public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	
	
	
}
