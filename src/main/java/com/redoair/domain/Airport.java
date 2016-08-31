
package com.redoair.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airports")
public class Airport  implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String city;

	private String country;
	
	@Column(name="IATA_FAA")
	private String iata;
	
	@Column(name="ICAO")
	private String icaoCode;	
	
//zou BigDecimal moeten zijn maar geeft een cast error
	private String lattitude;
	//zou BigDecimal moeten zijn maar geeft een cast error
	private String longitude;
	
	private int altitude;
	
	private Double timezone;
	
	private String timezone_tz;
	
	@Column(name="DST")
	private String dst;
	
	private String region;
}
