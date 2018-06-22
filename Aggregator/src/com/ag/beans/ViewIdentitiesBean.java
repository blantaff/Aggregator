package com.ag.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.ag.objects.Identity;
import com.ag.services.IdentityService;

@ManagedBean(name="viewIdentitiesBean")
@ViewScoped
public class ViewIdentitiesBean {

	@SuppressWarnings("unused")
	private List<Identity> identityList;

	public List<Identity> getIdentityList() {
		return IdentityService.getIdentities();
	}

	public void setIdentityList(List<Identity> identityList) {
		this.identityList = identityList;
	}


}
