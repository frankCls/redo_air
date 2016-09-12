package com.redoair.web.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Size;

import org.mindrot.jbcrypt.BCrypt;

import com.redoair.domain.Role;
import com.redoair.domain.User;
import com.redoair.services.UserRemote;
import com.redoair.services.UserService;
import com.redoair.web.utils.SessionUtils;

@ManagedBean(name = "userBean")
@RequestScoped
public class UserBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1759509149313742759L;

	// private String firstName;
	// private String lastName;
	// @Size(min = 8, max = 30)
	// private String password;
	//
	// @Size(min = 4, max = 30)
	// private String email;
	//
	// @Size(min = 4, max = 30)
	// private String userName;
	private User user=new User();

	@ManagedProperty(value = "#{flightId}")
	private String userData;

	@EJB
	private UserService userService;

	public String saveUser() {
		System.out.println(this.user.getLastName());
		User user = new User();
		user.setFirstName(this.user.getFirstName());
		user.setLastName(this.user.getLastName());
		user.setPassword(hashPassword(this.user.getPassword()));
		user.setEmail(this.user.getEmail());
		user.setUserName("null");
		user.setRole(Role.PAYER);
		if (userService.saveUser(user) != null) {

			setSessionAttributes(user);
			return "UserSaved";

		} else {
			return "FailUserSaved";
		}
	}

	public String login() {
		
		System.out.println("in login()");
		
		User checkUser = userService.findUserByEmail(user.getEmail());
		if (checkUser==null) {
			System.out.println("no user found!");
			return "noUser";
		}else if (checkUser.getEmail().equals(user.getEmail()) && checkUser.getRole().equals(Role.PAYER)
				&& checkPassword(user.getPassword(), checkUser.getPassword())) {
			setSessionAttributes(checkUser);
			System.out.println("user is found");
			return "payer" ;
		} else if (checkUser.getEmail().equals(user.getUserName()) && checkUser.getRole() == Role.PARTNER
				&& checkPassword(user.getPassword(), checkUser.getPassword())) {
			setSessionAttributes(checkUser);
			return "partner" ;
		} else {
			System.out.println("login failed");
			return "invalid" ;
		}
		
	}

	public void setSessionAttributes(User user) {
		HttpSession session = SessionUtils.getSession();

		session.setAttribute("email", user.getEmail());
		session.setAttribute("firstName", user.getFirstName());
		session.setAttribute("lastName", user.getLastName());
		session.setAttribute("role", user.getRole());

		if (session.getAttribute("flightId") != null && session.getAttribute("tickets") != null) {
			System.out.println(session.getAttribute("flightId").toString());
			session.setAttribute("flightId", session.getAttribute("flightId").toString());
			session.setAttribute("tickets", session.getAttribute("tickets").toString());
		}

	}

	public String getUserData() {
		return userData;
	}

	public void setUserData(String userData) {
		this.userData = userData;
	}

	public String hashPassword(String password_plaintext) {
		String salt = BCrypt.gensalt(12);
		String hashed_password = BCrypt.hashpw(password_plaintext, salt);

		return (hashed_password);
	}

	public boolean checkPassword(String password_plaintext, String stored_hash) {
		boolean password_verified = false;

		if (null == stored_hash || !stored_hash.startsWith("$2a$"))
			throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

		password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

		return (password_verified);
	}

	public void logout() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		FacesContext.getCurrentInstance().getExternalContext().redirect(SessionUtils.getRequest().getContextPath()+"/index.jsf");
		
		
	}

	// public String getEmail() {
	// return email;
	// }
	//
	// public void setEmail(String email) {
	// this.email = email;
	// }
	//
	// public String getFirstName() {
	// return firstName;
	// }
	//
	// public void setFirstName(String firstName) {
	// this.firstName = firstName;
	// }
	//
	// public String getLastName() {
	// return lastName;
	// }
	//
	// public void setLastName(String lastName) {
	// this.lastName = lastName;
	// }
	//
	// public String getPassword() {
	// return password;
	// }
	//
	// public void setPassword(String password) {
	// this.password = password;
	// }
	//
	// public String getUserName() {
	// return userName;
	// }
	//
	// public void setUserName(String userName) {
	// this.userName = userName;
	// }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
