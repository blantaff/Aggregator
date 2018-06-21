package com.ag.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import com.ag.objects.Identity;
import com.ag.services.IdentityService;

@ManagedBean
@RequestScoped
public class CreateIdentityBean {

	Identity identity = new Identity();

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	public void createIdentity() {

		if(IdentityService.createIdentity(identity)) {
			this.identity = new Identity();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success:", "New Identity was created."));
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error:", "New Identity was NOT created."));
		}

	}

}
