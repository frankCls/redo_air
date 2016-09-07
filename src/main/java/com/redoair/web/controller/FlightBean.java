
package com.redoair.web.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.constraints.Future;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.redoair.domain.Flight;
import com.redoair.domain.TravelingClassType;
import com.redoair.services.FlightService;

@ManagedBean(name = "flightBean")
@SessionScoped

public class FlightBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4942143979117129063L;

	@Inject
	private FlightService flightService;

	private String depCountry = "";
	private String destCountry = "";
	private String depRegion = "";
	private String destRegion = "";
	private TravelingClassType travelingClass = TravelingClassType.ECONOMY_CLASS;
	private Date depDate;
	private int nrOfTickets = 1;
	@Future
	private Date fromDate;

	@Future
	private Date toDate;
	boolean renderFlightsList = false;

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
		destCountryList = flightService.findAllDestinationCountries();
		depRegionList = flightService.findAllDepartureRegions();
		destRegionList = flightService.findAllDestinationRegions();
	}

	public void getFlightsForSearchCriteria() {
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
		list.forEach(s->System.out.println(s.getId()));
		List<Flight> tempList = new ArrayList<>();
		System.out.println(depDate  + "= depDate");
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
						.filter(s -> (depDate.before(s.getDepartureTime())
								|| depDate.equals(s.getDepartureTime()))
								&& s.getDepartureTime().before(toDate))
						.collect(Collectors.toList());

			}
			if (travelingClass.equals(TravelingClassType.BUSINESS_CLASS)) {
				tempList = list.stream()
						.filter(s -> s.getFlightData().getBusinnessClass().getRemainingSeats() >= nrOfTickets)
						.filter(s -> (depDate.before(s.getDepartureTime())
								|| depDate.equals(s.getDepartureTime()))
								&& s.getDepartureTime().before(toDate))
						.collect(Collectors.toList());
			}
			if (travelingClass.equals(TravelingClassType.FIRST_CLASS)) {
				tempList = list.stream()
						.filter(s -> s.getFlightData().getBusinnessClass().getRemainingSeats() >= nrOfTickets)
						.filter(s -> (depDate.before(s.getDepartureTime())
								|| depDate.equals(s.getDepartureTime()))
								&& s.getDepartureTime().before(toDate))
						.collect(Collectors.toList());
			}
			flightsList=tempList;
			
		}
		System.out.println("after filtering");
		flightsList.forEach(s->System.out.println(s.getId()));
	}

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		// facesContext.addMessage(null, new
		// FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected",
		// format.format(event.getObject())));
	}

	public void click() {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.update("form:display");
		requestContext.execute("PF('dlg').show()");
	}

	public void fillDepartureAndOrDestinationCountriesList() {
		// if(flight.getDepartureLocation().getCountry()==null)
	}

	public void toggleView() {
		renderFlightsList = !renderFlightsList;
	}

	public String search() {
		System.err.println("date: " + depDate);
		System.out.println();
		System.err.println("in search()");
		this.getFlightsForSearchCriteria();

		if (this.flightsList.size() > 0) {
			flightsList.forEach(s -> System.out.println(s.getDepartureTime().toString()));
		}

		return "search_results";
	}

	public boolean isRenderFlightsList() {
		return renderFlightsList;
	}

	public void setRenderFlightsList(boolean renderFlightsList) {
		this.renderFlightsList = renderFlightsList;

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
