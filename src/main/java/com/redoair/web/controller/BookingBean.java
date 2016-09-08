package com.redoair.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.redoair.domain.Booking;
import com.redoair.domain.CreditCard;
import com.redoair.domain.CreditCardType;
import com.redoair.domain.Flight;
import com.redoair.domain.Passenger;
import com.redoair.domain.Payer;
import com.redoair.domain.PurchaseStatus;
import com.redoair.domain.Ticket;
import com.redoair.domain.TravelingClassType;
import com.redoair.services.BookingServiceEjb;
import com.redoair.services.FlightService;
import com.redoair.web.utils.SessionUtils;

@Named(value = "bookingBean")
@ConversationScoped
public class BookingBean implements Serializable {

	private static final long serialVersionUID = 2180718255525138721L;

	private Booking booking = new Booking();

	private Payer payer = new Payer();

	private String creditCardNumber = "";

	@Inject
	private BookingServiceEjb bookingServiceEjb;
	@Inject
	private FlightBean flightBean;

	private List<Ticket> tickets = new ArrayList<>();

	private Flight flight;

	@Inject
	private FlightService flightService;

	private double calculatedPrice = 0;

	private CreditCardType selectedCreditCardType = CreditCardType.MASTER_CARD;
	private List<String> CreditCardtypeList = Arrays
			.asList(Stream.of(CreditCardType.values()).map(CreditCardType::name).toArray(String[]::new));

	@Inject
	private Conversation conversation;

	public void end() {
		if (!conversation.isTransient()) {

			conversation.end();

		}
	
	}

	// Navigation
	public String goToBooking() {
		if (flight == null || conversation == null) {
			return "";// Dont go anywhere if the there was no input the field
		}
		conversation.begin();

		return "booking.jsf?faces-redirect=true";
	}

	public void initConversation() {

		if (!FacesContext.getCurrentInstance().isPostback() && conversation.isTransient()) {
			conversation.begin();
		}
	}

	@PostConstruct
	public void init() {
		flight = flightService.findFlightById(17313L);
		flightBean.setFlight(flight);

		flightBean.setNrOfTickets(2);

		flightBean.setTravelingClass(TravelingClassType.ECONOMY_CLASS);
		// --------

		payer.setFirstName(SessionUtils.getFirstName());
		payer.setLastName(SessionUtils.getLastName());

		for (int i = 1; i <= flightBean.getNrOfTickets(); i++) {
			Ticket ticket = new Ticket();
			ticket.setFlight(flight);
			ticket.setTravelingClass(flightBean.getTravelingClass());

			ticket.setPassenger(new Passenger());
			tickets.add(ticket);
		}

	}

	public String saveBooking() {

		CreditCard creditCard = new CreditCard();
		// System.err.println("in save booking()");
		// System.out.println(getSelectedCreditCardType());
		if (creditCardNumber != null) {
			creditCard.setCreditCardNumber(Long.parseLong(creditCardNumber));
			// System.out.println("card number: " +
			// creditCard.getCreditCardNumber());

			List<Payer> findPayersList = bookingServiceEjb.findPayersByLastNameAndFirstName(payer);

			if (findPayersList.isEmpty()) {

				// System.err.println("No payer found");

				creditCard.setTypeCreditCard(getSelectedCreditCardType());
				payer.addCreditCard(creditCard);
				booking.setPayer(payer);

			}

			else if (!findPayersList.isEmpty()) {
				// System.err.println("payer found");
				for (Payer payer2 : findPayersList) {
					// System.out.println(" payers name: " +
					// payer2.getFirstName());
					boolean foundCreditCard = false;
					for (CreditCard card : payer2.getCreditCard()) {

						if (card.getCreditCardNumber() == creditCard.getCreditCardNumber()) {
							foundCreditCard = true;
						}
					}
					if (!foundCreditCard) {
						// System.out.println("creditcard not found");
						creditCard.setTypeCreditCard(getSelectedCreditCardType());

						payer2.addCreditCard(creditCard);

					} else {
						// System.out.println("creditcard found");
						creditCard.setTypeCreditCard(getSelectedCreditCardType());

					}
					booking.setPayer(payer2);
				}

			}
			if (getSelectedCreditCardType() == CreditCardType.ENDORSEMENT) {
				booking.setPurchaseStatus(PurchaseStatus.PENDING);

			} else {

				booking.setPurchaseStatus(PurchaseStatus.COMPLETED);

			}
			for (Ticket t : tickets) {
				booking.addTickets(t);
			}
			System.out.println("traveling class: " + flightBean.getTravelingClass());
			System.out.println("flightdataId: " + flight.getFlightData().getId());

			int remainingSeats = flight.getFlightData().getEconomyClass().getRemainingSeats();
			System.out.println("remainingSeats: " + remainingSeats);
			flight.getFlightData().getEconomyClass().setRemainingSeats(remainingSeats - flightBean.getNrOfTickets());
			System.out.println(
					"remainingSeats after edit: " + flight.getFlightData().getEconomyClass().getRemainingSeats());

			flightService.updateFlight(flight);
			flight = flightService.findFlightById(17313L);
			System.out.println("flight after merge:" + flight.getFlightData().getEconomyClass().getRemainingSeats());
			bookingServiceEjb.saveBooking(booking);

		}
		end();
		return "bookingSuccess";

	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public double calculatePrice() {
		double ticketBasePrice = 0;
		double discount = 0;
		if (selectedCreditCardType != null) {

			System.out.println(selectedCreditCardType);
			if (!selectedCreditCardType.equals(CreditCardType.ENDORSEMENT)) {
				discount = 0.1;

			} else {
				discount = 0;
			}
			System.err.println("dicount" + discount);
			if (flightBean.getTravelingClass().equals(TravelingClassType.ECONOMY_CLASS)) {
				ticketBasePrice = flight.getFlightData().getEconomyClass().getPricing().getBasePrice()
						* (1 + flight.getFlightData().getEconomyClass().getPricing().getDefaultPrice());

			} else if (flightBean.getTravelingClass().equals(TravelingClassType.BUSINESS_CLASS)) {
				ticketBasePrice = flight.getFlightData().getBusinnessClass().getPricing().getBasePrice()
						* (1 + flight.getFlightData().getBusinnessClass().getPricing().getDefaultPrice());

			} else if (flightBean.getTravelingClass().equals(TravelingClassType.FIRST_CLASS)) {
				ticketBasePrice = flight.getFlightData().getFirstClass().getPricing().getBasePrice()
						* (1 + flight.getFlightData().getFirstClass().getPricing().getDefaultPrice());

			}
			calculatedPrice = ticketBasePrice - (ticketBasePrice * discount);

		}
		return calculatedPrice;

	}

	public void selectOneMenuListener(ValueChangeEvent event) {

		Object newValue = event.getNewValue();
		creditCardNumber = "";
		double ticketBasePrice = 0;
		double discount = 0;
		if (newValue != null) {
			selectedCreditCardType = CreditCardType.valueOf(newValue.toString());
			System.out.println("selectedCreditCardType: " + selectedCreditCardType);

		}
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Payer getPayer() {
		return payer;
	}

	public void setPayer(Payer payer) {
		this.payer = payer;
	}

	public double getCalculatedPrice() {
		return calculatedPrice;
	}

	public void setCalculatedPrice(double calculatedPrice) {
		this.calculatedPrice = calculatedPrice;
	}

	public List<String> getCreditCardtypeList() {
		return CreditCardtypeList;
	}

	public void setCreditCardtypeList(List<String> creditCardtypeList) {
		CreditCardtypeList = creditCardtypeList;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public FlightBean getFlightBean() {
		return flightBean;
	}

	public void setFlightBean(FlightBean flightBean) {
		this.flightBean = flightBean;
	}

	public CreditCardType getSelectedCreditCardType() {
		return selectedCreditCardType;
	}

	public void setSelectedCreditCardType(CreditCardType selectedCreditCardType) {
		this.selectedCreditCardType = selectedCreditCardType;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

}
