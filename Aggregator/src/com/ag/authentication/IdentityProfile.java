package com.ag.authentication;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ag.objects.Identity;

@ManagedBean
@SessionScoped
public class IdentityProfile {

	Identity identity = new Identity();
	String Auth_Key = "";
	
	public Identity getIdentity() {
		return identity;
	}
	public void setIdentity(Identity identity) {
		this.identity = identity;
	}
	public String getAuth_Key() {
		return Auth_Key;
	}
	public void setAuth_Key(String auth_Key) {
		Auth_Key = auth_Key;
	}
}