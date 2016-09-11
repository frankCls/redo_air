package com.redoair.web.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.redoair.domain.Booking;
import com.redoair.web.utils.SessionUtils;

@ManagedBean(name = "qrCodeController")
@ViewScoped
public class QRCodeController implements Serializable {

	private static final long serialVersionUID = 20120316L;
	private String renderMethod;

	private Booking booking = new Booking();

	private String label;
	private int mode;
	private int size;
	private String fillColor;

	@PostConstruct
	public void init() throws IOException {
		System.out.println("in init qr");
		HttpSession session = SessionUtils.getSession();
		if (session.getAttribute("myBooking") != null) {
			booking = (Booking) session.getAttribute("myBooking");
		}else {
			FacesContext.getCurrentInstance().getExternalContext().responseSendError(400, "sorry something went wrong");
			
		}
	
		System.out.println(booking.getId());

	}

	public QRCodeController() {
		renderMethod = "canvas";

		System.out.println("text " + booking.getId());
		// text = "http://primefaces-extensions.github.io/";
		
		mode = 0;
		fillColor = "000000";
		size = 300;
	}

	public String getRenderMethod() {
		return renderMethod;
	}

	public void setRenderMethod(String renderMethod) {
		this.renderMethod = renderMethod;
	}



	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public String getFillColor() {
		return fillColor;
	}

	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
