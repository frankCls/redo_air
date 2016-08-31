
package com.redoair.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Airport {
	@Id @GeneratedValue
	private Long id;
	
	private String name;
	
	private String city;

	private String country;
	
	@Column(name="IATA_FAA")
	private String iata;
	
	@Column(name="ICAO")
	private String icaoCode;	

	private BigDecimal lattitude;
	
	private BigDecimal longitude;
	
	private int altitude;
	
	private Double timezone;
	
	@Column(name="DST")
	private String dst;
	
	private String region;
}
