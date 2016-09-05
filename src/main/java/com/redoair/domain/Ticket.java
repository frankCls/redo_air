package com.redoair.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import com.redoair.domain.TravelingClassType;

@Entity
public class Ticket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9025140047545706323L;

	@Id
	@GeneratedValue
	private Long id;

	@Version
	private Long version;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TravelingClassType travelingClass;

	@NotNull
	@ManyToOne
	private Flight flight;

	@ManyToOne
	@JoinColumn(name = "booking_id")
	private Booking booking;

	@NotNull
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
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

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

}
