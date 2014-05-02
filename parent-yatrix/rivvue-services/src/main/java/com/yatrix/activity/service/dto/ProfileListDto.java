package com.yatrix.activity.service.dto;

import java.util.List;



public class ProfileListDto {

	/**
	 * @return the profiles
	 */
	public List<UserDto> getProfiles() {
		return profiles;
	}

	/**
	 * @param pProfiles
	 *          the profiles to set
	 */
	public void setProfiles(List<UserDto> pProfiles) {
		profiles = pProfiles;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



	private List<UserDto> profiles;

	private Integer status;

	private String message;

}
