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

  private List<UserDto> profiles;

}
