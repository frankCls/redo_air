
package com.redoair.domain;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airports")
public class Airport  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String city;

	private String country;

	@Column(name = "IATA_FAA")
	private String iata;

	@Column(name = "ICAO")
	private String icaoCode;

	private String lattitude;

	private String longitude;

	private int altitude;

	private Double timezone;

	@Column(name = "timezone_tz")
	private String timezoneTz;

	@Column(name = "DST")	
	private String dst;

	private String region;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getIata() {
		return iata;
	}

	public void setIata(String iata) {
		this.iata = iata;
	}

	public String getIcaoCode() {
		return icaoCode;
	}

	public void setIcaoCode(String icaoCode) {
		this.icaoCode = icaoCode;
	}

	public String getLattitude() {
		return lattitude;
	}

	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public int getAltitude() {
		return altitude;
	}

	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}

	public Double getTimezone() {
		return timezone;
	}

	public void setTimezone(Double timezone) {
		this.timezone = timezone;
	}

	public String getDst() {
		return dst;
	}

	public void setDst(String dst) {
		this.dst = dst;
	}

	public String getTimezoneTz() {
		return timezoneTz;
	}

	public void setTimezoneTz(String timezoneTz) {
		this.timezoneTz = timezoneTz;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

}
