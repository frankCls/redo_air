package com.redoair.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

@Entity
public class Flight implements Serializable {
	@Id @GeneratedValue
	private Long id;
	
	@NotNull
	@Column(nullable=false)
	private Long duration;
		
	@NotNull
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@Future
	private Date departureTime;
	
	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name="depID", nullable=false)
	private Airport departureLocation;
	
	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name="destID", nullable=false)
	private Airport destinationLocation;
	
	@Embedded
	private FirstClassData firstClassData;
	
	@Embedded	
	private EconomyClassData economyClassData;
	
	@Embedded
	private BusinessClassData businessClassData;

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

	public FirstClassData getFirstClassData() {
		return firstClassData;
	}

	public void setFirstClassData(FirstClassData firstClassData) {
		this.firstClassData = firstClassData;
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
	
	
}
