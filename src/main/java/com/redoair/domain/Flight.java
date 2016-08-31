package com.redoair.domain;

import java.util.Date;

public class Flight {
	
	private Long duration;

	private Airport departureLocation;
	
	private Date departureTime;

	private Airport destinationLocation;
	
	private FirstClassData firstClassData;

	private EconomyClassData economyClassData;
	
	private BusinessClassData businessClassData;
}
