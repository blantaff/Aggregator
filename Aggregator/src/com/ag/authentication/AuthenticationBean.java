package com.ag.authentication;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import com.ag.objects.Identity;
import com.ag.services.AuthenticationService;

@ManagedBean
@RequestScoped
public class AuthenticationBean {

	private String loginId;
	private String password;
	private boolean isValidUser = false;
	private String LOGIN_URL = "/Aggregator/ag/index.xhtml";
	private String LOGOUT_URL = "/Aggregator/login.xhtml";

	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isLoggedIn() {

		FacesContext context = FacesContext.getCurrentInstance();
		IdentityProfile p = (IdentityProfile) context.getExternalContext().getSessionMap().get("userProfile");
		String auth = p.getAuth_Key();
		return auth != null;
	}

	/**
	 * Login method validates user, loads the user profile and then sends the user to the main page.
	 * @param up - UserProfile Bean
	 * @return
	 */
	public String login(IdentityProfile ip) {

		isValidUser = AuthenticationService.isValidUser(loginId, password);

		if (isValidUser){

			//Set Profile data
			Identity identity = AuthenticationService.getUserProfile(loginId, password);
			ip.setIdentity(identity);

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
					"Auth_Key", "valid");
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
					"LoginId", ip.getIdentity().getLoginId());

			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext ec = context.getExternalContext();

			try {
				ec.redirect(LOGIN_URL);
			} catch (IOException e) {
				System.out.println(e);
			}

		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error:", "Incorrect username and/or password!"));
		}
		return "secret";
	}

	/**
	 * Logout method returns user to login page.
	 * @return
	 */
	public String logout() {

		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		ec.getSessionMap().clear();

		try {
			ec.redirect(LOGOUT_URL);
		} catch (IOException e) {
			System.out.println(e);
		}
		return null;
	}

}
