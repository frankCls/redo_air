package com.redoair.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import com.redoair.domain.Flight;
import com.redoair.domain.TravelingClassType;
import com.redoair.services.CalculatePriceServiceEjb;
import com.redoair.services.FlightService;

@ManagedBean(name = "searchResultsBean")
@RequestScoped
public class SearchResultsBean {

	@Inject
	private FlightService flightService;

	@Inject
	private CalculatePriceServiceEjb priceCalculator;
//	@ManagedProperty(name = "depCountry", value = "#{param.depCountry}")
	private String depCountry;
//	@ManagedProperty(name = "destCountry", value = "#{param.destCountry}")
	private String destCountry;
//	@ManagedProperty(name = "depRegion", value = "#{param.depRegion}")
	private String depRegion;
//	@ManagedProperty(name = "destRegion", value = "#{param.destRegion}")
	private String destRegion;
//	@ManagedProperty(name = "travelingClass", value = "#{param.travelingClass}")
	private TravelingClassType travelingClass;
//	@ManagedProperty(name = "nrOfTickets", value = "#{param.nrOfTickets}")
	private int nrOfTickets;
	private Long flightId;

	private Date depDate;
	private Date toDate;

	private boolean renderEconomyPrice = false;
	private boolean renderBusinessPrice = false;
	private boolean renderFirstClassPrice = false;
	private List<Flight> flightsList = new ArrayList<>();

	public boolean isRenderEconomyPrice() {
		return renderEconomyPrice;
	}

	@PostConstruct
	public void init() {
		System.out.println(depCountry + "  " + travelingClass + " " + nrOfTickets);
		search();
	}

	public String search() {
		System.err.println("in search()");
		this.getFlightsForSearchCriteria();
		return "success";
	}

	public void searchResultListener(ActionEvent event) {
		depCountry = (String) event.getComponent().getAttributes().get("depCountry");
		destCountry = (String) event.getComponent().getAttributes().get("destCountry");
		depRegion = (String) event.getComponent().getAttributes().get(depRegion);
		destRegion = (String) event.getComponent().getAttributes().get(destRegion);
		travelingClass = (TravelingClassType) event.getComponent().getAttributes().get(travelingClass);
		nrOfTickets = (Integer) event.getComponent().getAttributes().get(nrOfTickets);
	}

	private void getFlightsForSearchCriteria() {
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

	public Date getDepDate() {
		return depDate;
	}

	public void setDepDate(Date depDate) {
		this.depDate = depDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getNrOfTickets() {
		return nrOfTickets;
	}

	public void setNrOfTickets(int nrOfTickets) {
		this.nrOfTickets = nrOfTickets;
	}

	public void setRenderEconomyPrice(boolean renderEconomyPrice) {
		this.renderEconomyPrice = renderEconomyPrice;
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

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public List<Flight> getFlightsList() {
		return flightsList;
	}

	public void setFlightsList(List<Flight> flightsList) {
		this.flightsList = flightsList;
	}
}
