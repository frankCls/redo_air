package com.redoair.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
public class Booking implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6700641769939449352L;

	@Id
	@GeneratedValue
	private Long id;

	@Version
	private Long version;

	@OneToMany(mappedBy = "booking", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, fetch = FetchType.EAGER)
	private List<Ticket> tickets = new ArrayList<>();

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateBooked;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY/* , cascade=CascadeType.PERSIST */)
	private Payer payer;

	@NotNull
	@Enumerated(EnumType.STRING)
	private PurchaseStatus purchaseStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void addTickets(Ticket ticket) {
		ticket.setBooking(this);
		tickets.add(ticket);
	}

	public void removeTickets() {
		for (Ticket t : tickets) {
			t.setBooking(null);
		}
		tickets.clear();
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Date getDateBooked() {
		return dateBooked;
	}

	public void setDateBooked(Date dateBooked) {
		this.dateBooked = dateBooked;
	}

	public Payer getPayer() {
		return payer;
	}

	public void setPayer(Payer payer) {
		this.payer = payer;
	}

	public PurchaseStatus getPurchaseStatus() {
		return purchaseStatus;
	}

	public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

}
