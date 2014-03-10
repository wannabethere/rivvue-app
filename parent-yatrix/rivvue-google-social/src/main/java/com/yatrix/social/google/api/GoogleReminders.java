package com.yatrix.social.google.api;

import java.io.Serializable;
import java.util.List;

public class GoogleReminders implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2201223033456925680L;
	private List<GoogleOverrides> overrides;

	public List<GoogleOverrides> getOverrides() {
		return overrides;
	}

	public void setOverrides(List<GoogleOverrides> overrides) {
		this.overrides = overrides;
	}

}
