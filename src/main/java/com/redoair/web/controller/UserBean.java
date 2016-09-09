package com.redoair.web.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Size;

import org.mindrot.jbcrypt.BCrypt;

import com.redoair.domain.Role;
import com.redoair.domain.User;
import com.redoair.services.UserRemote;
import com.redoair.web.utils.SessionUtils;

@ManagedBean(name = "userBean")
@RequestScoped
public class UserBean implements Serializable {

	private String firstName;
	private String lastName;
	@Size(min = 8, max = 30)
	private String password;

	@Size(min = 4, max = 30)
	private String email;

	@Size(min = 4, max = 30)
	private String userName;
	@EJB
	private UserRemote userService;

	public String saveUser() {
		System.out.println(userName);
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(hashPassword(password));
		user.setEmail(email);
		user.setUserName(userName);
		user.setRole(Role.PAYER);
		if (userService.saveUser(user) != null) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("userName", userName);
			session.setAttribute("firstName", firstName);
			session.setAttribute("lastName", lastName);
			session.setAttribute("role", user.getRole());
			return "UserSaved";

		} else {
			return "FailUserSaved";
		}
	}

	public String login() {
		
		System.out.println("in login()");
		User user = userService.findUserByUserName(userName);
		if (user == null) {
			System.out.println("no user found!");
			return "noUser";
		}

		if (userName.equals(user.getUserName()) && user.getRole() == Role.PAYER
				&& checkPassword(password, user.getPassword())) {
			setSessionAttributes(user);
			return "payer";
		} else if (userName.equals(user.getUserName()) && user.getRole() == Role.PARTNER
				&& checkPassword(password, user.getPassword())) {
			setSessionAttributes(user);
			return "partner";
		} else {
			System.out.println("login failed");
			return "invalid";
		}

	}

	public void setSessionAttributes(User user) {
		HttpSession session = SessionUtils.getSession();
		session.setAttribute("userName", user.getUserName());
		session.setAttribute("firstName", user.getFirstName());
		session.setAttribute("lastName", user.getLastName());
		session.setAttribute("role", user.getRole());
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

	public void logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), null, "/login.xhtml");

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}