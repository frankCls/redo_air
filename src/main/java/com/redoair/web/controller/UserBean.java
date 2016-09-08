package com.redoair.web.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.redoair.domain.User;
import com.redoair.services.UserRemote;
import com.redoair.web.utils.SessionUtils;

@ManagedBean(name = "userBean")
@RequestScoped
public class UserBean implements Serializable {

	private String firstName = "";
	private String lastName = "";
	private String password = "";
	private String userName = "";

	@EJB
	private UserRemote userService;

	public String saveUser() {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		user.setUserName(userName);

		if (userService.saveUser(user) != null) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("userName", userName);
			session.setAttribute("firstName", firstName);
			session.setAttribute("lastName", lastName);

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
		if (userName.equals(user.getUserName()) && password.equals(user.getPassword())) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("firstName", user.getFirstName());
			session.setAttribute("lastName", user.getLastName());

			return "output";
		} else {

			return "invalid";
		}

	}

	public void logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), null, "/login.xhtml");

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
