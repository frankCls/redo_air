package com.redoair.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
public class Booking  implements Serializable{

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToMany( cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
   	private Set<Ticket> tickets;

	@Temporal(TemporalType.DATE)
	private Date dateBooked;

	@NotNull
	@ManyToOne
	private Payer payer;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
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
	
	
}
