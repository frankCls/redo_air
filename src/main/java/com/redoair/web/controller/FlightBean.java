
package com.redoair.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Future;

import com.redoair.domain.Flight;
import com.redoair.domain.TravelingClassType;
import com.redoair.services.CalculatePriceServiceEjb;
import com.redoair.services.FlightService;

@Named("flightBean")
@ConversationScoped
public class FlightBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4942143979117129063L;

	@Inject
	private FlightService flightService;

	@Inject
	private CalculatePriceServiceEjb priceCalculator;

	@Inject
	private Conversation conversation;

	private String depCountry = "";
	private String destCountry = "";
	private String depRegion = "";
	private String destRegion = "";
	private TravelingClassType travelingClass = TravelingClassType.ECONOMY_CLASS;

	private String flightId;

	private Date depDate;
	private int nrOfTickets = 1;
	// @Future
	// private Date fromDate;

	@Future
	private Date toDate;

	private List<Flight> flightsList = new ArrayList<>();
	private List<String> depCountryList = new ArrayList<>();
	private List<String> destCountryList = new ArrayList<>();
	private List<String> depRegionList = new ArrayList<>();
	private List<String> destRegionList = new ArrayList<>();
	private List<String> travelingClassList = Arrays
			.asList(Stream.of(TravelingClassType.values()).map(TravelingClassType::name).toArray(String[]::new));
	private Map<Flight, Double> prices = new HashMap<>();
	private boolean renderEconomyPrice = false;
	private boolean renderBusinessPrice = false;
	private boolean renderFirstClassPrice = false;
	private Flight flightDetails;

	@PostConstruct
	public void init() {
		depCountryList = flightService.findAllDepartureCountries();
		destCountryList = flightService.findAllDestinationCountries();
		depRegionList = flightService.findAllDepartureRegions();
		destRegionList = flightService.findAllDestinationRegions();
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}

	public void getFlightsForSearchCriteria() {

		whichPriceToRender(travelingClass);

		System.err.println("in getFlightsForSearchCriteria()");
		if (depDate != null) {
			System.out.println("chosen date: " + depDate);
			Calendar c = Calendar.getInstance();
			c.setTime(depDate);
			c.add(Calendar.DATE, 1);
			toDate = c.getTime();
			System.out.println("max date: " + toDate);
		}

		List<Flight> list = flightService.findFlightsForSearchCriteria(this.depCountry, this.depRegion,
				this.destCountry, this.destRegion);
		System.out.println("initial querylist");
		list.forEach(s -> System.out.println(s.getId()));
		List<Flight> tempList = new ArrayList<>();
		System.out.println(depDate + "= depDate");
		if (depDate == null) {
			if (travelingClass.equals(TravelingClassType.ECONOMY_CLASS)) {
				tempList = list.stream()
						.filter(s -> s.getFlightData().getEconomyClass().getRemainingSeats() >= nrOfTickets)
						.collect(Collectors.toList());
			}
			if (travelingClass.equals(TravelingClassType.BUSINESS_CLASS)) {
				tempList = list.stream()
						.filter(s -> s.getFlightData().getBusinnessClass().getRemainingSeats() >= nrOfTickets)
						.collect(Collectors.toList());
			}
			if (travelingClass.equals(TravelingClassType.FIRST_CLASS)) {
				tempList = list.stream()
						.filter(s -> s.getFlightData().getBusinnessClass().getRemainingSeats() >= nrOfTickets)
						.collect(Collectors.toList());
			}
		} else {
			if (travelingClass.equals(TravelingClassType.ECONOMY_CLASS)) {
				tempList = list.stream()
						.filter(s -> s.getFlightData().getEconomyClass().getRemainingSeats() >= nrOfTickets)
						.filter(s -> (depDate.before(s.getDepartureTime()) || depDate.equals(s.getDepartureTime()))
								&& s.getDepartureTime().before(toDate))
						.collect(Collectors.toList());

			}
			if (travelingClass.equals(TravelingClassType.BUSINESS_CLASS)) {
				tempList = list.stream()
						.filter(s -> s.getFlightData().getBusinnessClass().getRemainingSeats() >= nrOfTickets)
						.filter(s -> (depDate.before(s.getDepartureTime()) || depDate.equals(s.getDepartureTime()))
								&& s.getDepartureTime().before(toDate))
						.collect(Collectors.toList());
			}
			if (travelingClass.equals(TravelingClassType.FIRST_CLASS)) {
				tempList = list.stream()
						.filter(s -> s.getFlightData().getBusinnessClass().getRemainingSeats() >= nrOfTickets)
						.filter(s -> (depDate.before(s.getDepartureTime()) || depDate.equals(s.getDepartureTime()))
								&& s.getDepartureTime().before(toDate))
						.collect(Collectors.toList());
			}
		}

		flightsList = tempList;
		this.setFlightsList(priceCalculator.calculatePrices(this.flightsList, this.travelingClass, this.nrOfTickets));
		System.out.println("contents of flightsList flightID's (filtered)*************************");
		this.flightsList.forEach(s -> System.out
				.println(s.getId() + " " + s.getFlightData().getEconomyClass().getPricing().getCalculatedPrice()));
		System.out.println("end of flightslist************************");

	}

	private void whichPriceToRender(TravelingClassType type) {
		if (type.equals(TravelingClassType.ECONOMY_CLASS)) {
			setRenderEconomyPrice(true);
			renderBusinessPrice = false;
			renderFirstClassPrice = false;
		}
		if (type.equals(TravelingClassType.BUSINESS_CLASS)) {
			setRenderEconomyPrice(false);
			renderBusinessPrice = true;
			renderFirstClassPrice = false;
		}
		if (type.equals(TravelingClassType.FIRST_CLASS)) {
			setRenderEconomyPrice(false);
			renderBusinessPrice = false;
			renderFirstClassPrice = true;
		}
	}

	public String search() {
		System.err.println("date: " + depDate);
		System.out.println();
		System.err.println("in search()");

		this.getFlightsForSearchCriteria();
		return "success";
	}

	public String detailsListener(Flight flight) {
		System.out.println("detail listener");
//		flightDetails = (Flight) event.getComponent().getAttributes().get("flight");
		flightDetails = flight;
		System.out.println("listener");
//		if(flightDetails.getId()!=null){
//			System.out.println(flightDetails.getId());
//		}
		
		return "details";
		//showDetails();
	}

//	public String showDetails() {
//		System.out.println("in showDetails");
//
//	}

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

	public Date getDepDate() {
		return depDate;
	}

	public void setDepDate(Date depDate) {
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

	public Map<Flight, Double> getPrices() {
		return prices;
	}

	public void setPrices(Map<Flight, Double> map) {
		this.prices = map;
	}

	public boolean isRenderBusinessPrice() {
		return renderBusinessPrice;
	}

	public void setRenderBusinessPrice(boolean renderBusinessPrice) {
		this.renderBusinessPrice = renderBusinessPrice;
	}

	public boolean isRenderFirstClassPrice() {
		return renderFirstClassPrice;
	}

	public void setRenderFirstClassPrice(boolean renderFirstClassPrice) {
		this.renderFirstClassPrice = renderFirstClassPrice;
	}

	public boolean isRenderEconomyPrice() {
		return renderEconomyPrice;
	}

	public void setRenderEconomyPrice(boolean renderEconomyPrice) {
		this.renderEconomyPrice = renderEconomyPrice;
	}

	public Flight getFlightDetails() {
		return flightDetails;
	}

	public void setFlightDetails(Flight flightDetails) {
		this.flightDetails = flightDetails;
	}

}