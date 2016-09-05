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
	/**
	 * 
	 */
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
	

	@OneToMany(mappedBy="flight")	
	private Set<AbstractTravelingClassData>travelingClassData=new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDuration() {
		return duration;
	}

	public Set<AbstractTravelingClassData> getTravelingClassData() {
		return travelingClassData;
	}

	public void setTravelingClassData(Set<AbstractTravelingClassData> travelingClassData) {
		this.travelingClassData = travelingClassData;
	}
	
	public void addTravelingClassData(AbstractTravelingClassData data){
		this.travelingClassData.add(data);
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

//	public FirstClassData getFirstClassData() {
//		return firstClassData;
//	}
//
//	public void setFirstClassData(FirstClassData firstClassData) {
//		this.firstClassData = firstClassData;
//	}
//
//	public EconomyClassData getEconomyClassData() {
//		return economyClassData;
//	}
//
//	public void setEconomyClassData(EconomyClassData economyClassData) {
//		this.economyClassData = economyClassData;
//	}
//
//	public BusinessClassData getBusinessClassData() {
//		return businessClassData;
//	}
//
//	public void setBusinessClassData(BusinessClassData businessClassData) {
//		this.businessClassData = businessClassData;
//	}
//	public AbstractTravelingClassData getTravelingClassData(TravelingClassType type){
//		AbstractTravelingClassData data=null;
//		switch(type.name()){
//		case "ECONOMY_CLASS" : data = this.getEconomyClassData();break;
//		case "BUSINESS_CLASS" : data = this.getBusinessClassData();break;
//		case "FIRST_CLASS" : data= this.getFirstClassData();break;		
//		}
//		return data;
//	}
	
	
}
