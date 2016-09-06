package com.redoair.web.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Future;

import com.redoair.domain.AbstractTravelingClassData;
import com.redoair.domain.Flight;
import com.redoair.domain.TravelingClassType;
import com.redoair.services.FlightService;

@ManagedBean(name="flightBean")
@SessionScoped
// @Path()
public class FlightBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4942143979117129063L;

	@Inject
	private FlightService flightService;

	private Flight flight = new Flight();
	// private Airport depLocation=new Airport();
	// private Airport destLocation=new Airport();
	private String depCountry = "";
	private String destCountry = "";
	private String depRegion = "";
	private String destRegion = "";
	private TravelingClassType travelingClass = TravelingClassType.ECONOMY_CLASS;
	private String depDate;
	private int nrOfTickets = 1;
	@Future
	private Date fromDate;

	@Future
	private Date toDate;
	boolean renderFlightsList= false;


	private List<Flight> flightsList = new ArrayList<>();
	private List<String> depCountryList = new ArrayList<>();
	private List<String> destCountryList = new ArrayList<>();
	private List<String> depRegionList = new ArrayList<>();
	private List<String> destRegionList = new ArrayList<>();
	private List<String> travelingClassList = Arrays
			.asList(Stream.of(TravelingClassType.values()).map(TravelingClassType::name).toArray(String[]::new));

	@PostConstruct
	public void init() {
		depCountryList = flightService.findAllDepartureCountries();
		// depCountryList.forEach(System.out::println);
		destCountryList = flightService.findAllDestinationCountries();
		// destCountryList.forEach(System.out::println);
		depRegionList = flightService.findAllDepartureRegions();
		destRegionList = flightService.findAllDestinationRegions();
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


	public void fillDepartureAndOrDestinationCountriesList() {
		// if(flight.getDepartureLocation().getCountry()==null)
	}
	
	public void toggleView(){
		renderFlightsList=!renderFlightsList;
	}

	public void search() {
		System.err.println(depDate);
		System.err.println("in search()");		
		this.getFlightsForSearchCriteria();

		if(this.flightsList.size()>0){
			flightsList.forEach(s->System.out.println(s.getDepartureLocation().getCity()));
			renderFlightsList=true;
		}else renderFlightsList=false;
		System.out.println(renderFlightsList);
	}

	public boolean isRenderFlightsList() {
		return renderFlightsList;
	}

	public void setRenderFlightsList(boolean renderFlightsList) {
		this.renderFlightsList = renderFlightsList;

	}

	public void getFlightsForDepAndDest() {
		flightsList = flightService.findFlightsByDepartureAndDestinationCountry(depCountry, destCountry);
		// flightsList.forEach(s->System.err.println(s.getDepartureLocation().getCity()
		// + " " + s.getDestinationLocation().getCity()));
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public List<Flight> getFlightsList() {
		return flightsList;
	}

	public void setFlightsList(List<Flight> flightsList) {
		this.flightsList = flightsList;
	}

	public List<String> getDestCountryList() {
		return destCountryList;
	}

	public void setDestCountryList(List<String> destCountryList) {
		this.destCountryList = destCountryList;
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

	public List<String> getDepCountryList() {
		return depCountryList;
	}

	public void setDepCountryList(List<String> depCountryList) {
		this.depCountryList = depCountryList;
	}

	public List<String> getTravelingClassList() {
		return travelingClassList;
	}

	public void setTravelingClassList(List<String> travelingClassList) {
		this.travelingClassList = travelingClassList;
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

	public List<String> getDepRegionList() {
		return depRegionList;
	}

	public void setDepRegionList(List<String> depRegionList) {
		this.depRegionList = depRegionList;
	}

	public List<String> getDestRegionList() {
		return destRegionList;
	}

	public void setDestRegionList(List<String> destRegionList) {
		this.destRegionList = destRegionList;
	}

	public TravelingClassType getTravelingClass() {
		return travelingClass;
	}

	public void setTravelingClass(TravelingClassType travelingClass) {
		this.travelingClass = travelingClass;
	}

	// public String
	// getAllFlightsByLocationsWithTravelingClassTypeAndSeatsAndDepartureDate()
	// {
	//
	// Date fromDate=null;
	// Date toDate=null;
	//
	// SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy-MM-dd
	// hh:mm:ss");
	//
	// try {
	// fromDate= timeStampFormat.parse("2017-04-30 09:00:00");
	// toDate= timeStampFormat.parse("2017-08-30 09:10:00");
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	//
	// //flightsList =
	// flightService.findAllFlightsByCountry(nameCountryArrival);
	// flightsList =
	// flightService.findFlightsByLocationsWithTravelingClassTypeAndSeatsAndDepartureDate(nameCountryArrival,
	// nameCountryDeparture, TravelingClassType.ECONOMY_CLASS,
	// 4,fromDate,toDate);
	//
	// //String depAirport,String destAirport, TravelingClassType
	// travelingClass, Integer seats, Date fromDate, Date toDate
	//
	// return null;
	// }

}
