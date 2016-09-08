package com.redoair.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

@Entity
public class Flight implements Serializable {
	
	private static final long serialVersionUID = -6067918148882402340L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(nullable=false)
	private Long duration;
		
	@NotNull
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@Future
	private Date departureTime;
	
	@NotNull
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="depID", nullable=false)
	private Airport departureLocation;
	
	@NotNull
	@OneToOne(fetch=FetchType.EAGER,cascade={CascadeType.MERGE})
	@JoinColumn(name="destID", nullable=false)
	private Airport destinationLocation;
	

	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	@JoinColumn(name="flightDataId")
	private FlightData flightData;
//	@NotNull
//	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
//	@JoinColumn(name="firstClassId")
//	private FirstClassData firstClassData;
//	
//	@NotNull
//	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
//	@JoinColumn(name="economyClassId")
//	private EconomyClassData economyClassData;
//	
//	@NotNull
//	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
//	@JoinColumn(name="businessClassId")
//	private BusinessClassData businessClassData;
	

//	@OneToMany(mappedBy="flight")	
//	private Set<AbstractTravelingClassData>travelingClassData=new HashSet<>();




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDuration() {
		return duration;
	}


	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public Airport getDepartureLocation() {
		return departureLocation;
	}

	public void setDepartureLocation(Airport departureLocation) {
		this.departureLocation = departureLocation;
	}

	public Airport getDestinationLocation() {
		return destinationLocation;
	}

	public void setDestinationLocation(Airport destinationLocation) {
		this.destinationLocation = destinationLocation;
	}

	public FlightData getFlightData() {
		return flightData;
	}

	public void setFlightData(FlightData flightData) {
		this.flightData = flightData;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Flight other = (Flight) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	

	

	
}
