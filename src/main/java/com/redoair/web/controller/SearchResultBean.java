package com.redoair.web.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.redoair.domain.Flight;
import com.redoair.domain.TravelingClassType;
import com.redoair.services.FlightService;

@ManagedBean(name="searchBean")
@ViewScoped
public class SearchResultBean  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1399788501799083811L;
	@Inject
	private FlightService flightService;
	
	private String depCountry = "";
	private String destCountry = "";
	private String depRegion = "";
	private String destRegion = "";
	private TravelingClassType travelingClass = TravelingClassType.ECONOMY_CLASS;
	private String depDate;
	private int nrOfTickets = 1;
	private Date fromDate;
	private Date toDate;
	
	private List<Flight> flightsList = new ArrayList<>();

	@PostConstruct
	public void init(){
		
	}
	public List<Flight> getFlightsList() {
		return flightsList;
	}

	public void setFlightsList(List<Flight> flightsList) {
		this.flightsList = flightsList;
	}
	
	public FlightService getFlightService() {
		return flightService;
	}

	public void setFlightService(FlightService flightService) {
		this.flightService = flightService;
	}

	public String getDepCountry() {
		return depCountry;
	}

	public void setDepCountry(String depCountry) {
		this.depCountry = depCountry;
	}

	public String getDestCountry() {
		return destCountry;
	}

	public void setDestCountry(String destCountry) {
		this.destCountry = destCountry;
	}

	public String getDepRegion() {
		return depRegion;
	}

	public void setDepRegion(String depRegion) {
		this.depRegion = depRegion;
	}

	public String getDestRegion() {
		return destRegion;
	}

	public void setDestRegion(String destRegion) {
		this.destRegion = destRegion;
	}

	public TravelingClassType getTravelingClass() {
		return travelingClass;
	}

	public void setTravelingClass(TravelingClassType travelingClass) {
		this.travelingClass = travelingClass;
	}

	public String getDepDate() {
		return depDate;
	}

	public void setDepDate(String depDate) {
		this.depDate = depDate;
	}

	public int getNrOfTickets() {
		return nrOfTickets;
	}

	public void setNrOfTickets(int nrOfTickets) {
		this.nrOfTickets = nrOfTickets;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void getFlightsForSearchCriteria() {
		System.err.println("in getFlightsForSearchCriteria()");

		// en dit mag uit comments dan
/*
		if (fromDate == null && toDate == null) {
			flightsList = flightService.findFlightsForSearchCriteria(this.depCountry, this.depRegion, this.destCountry,
					this.destRegion);

		} else {
*/
			/* Dit mag weg als de webpagina ook datums kan mee geven */
			SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			try {
				fromDate = timeStampFormat.parse("2016-09-29 09:00:00");
				toDate = timeStampFormat.parse("2017-08-30 09:10:00");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			/* Dit mag weg als de webpagina ook datums kan mee geven */

			flightsList = flightService.findFlightsByLocationsWithTravelingClassTypeAndSeatsAndDepartureDate(depCountry,
					destCountry, travelingClass, nrOfTickets, fromDate, toDate);
		}

	//}


	public void getFlightsForDepAndDest() {
		flightsList = flightService.findFlightsByDepartureAndDestinationCountry(depCountry, destCountry);
		// flightsList.forEach(s->System.err.println(s.getDepartureLocation().getCity()
		// + " " + s.getDestinationLocation().getCity()));
	}
}
