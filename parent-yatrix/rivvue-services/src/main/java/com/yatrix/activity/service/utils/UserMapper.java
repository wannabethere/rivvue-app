package com.yatrix.activity.service.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import com.yatrix.activity.service.mongo.dto.UserDto;
import com.yatrix.activity.store.mongo.domain.UserAccount;
import com.yatrix.activity.store.mongo.domain.UserProfile;
import com.yatrix.activity.store.mongo.domain.UserProfile.PROFILETYPE;

@SuppressWarnings("deprecation")
public class UserMapper {

  @SuppressWarnings("boxing")
  public static UserDto map(UserAccount user) {
    UserDto dto = new UserDto();
    dto.setId(user.getUserId());
    dto.setFirstName(user.getFirstName());
    dto.setLastName(user.getLastName());
    dto.setUsername(user.getUsername());
    dto.setRole((user.getRoles()[0]).name());
    dto.setEmail(user.getEmail());
    dto.setSrcProfileType(PROFILETYPE.APP);
    
    return dto;
  }

  public static UserAccount map(UserDto dto) {
    UserAccount user = new UserAccount();
    user.setFirstName(dto.getFirstName());
    user.setLastName(dto.getLastName());
    user.setUsername(dto.getUsername());
    user.setUserId(dto.getUsername());

    PasswordEncoder encoder = new Md5PasswordEncoder();
    user.setPassword(encoder.encodePassword(dto.getPassword(), null));
    return user;
  }

  public static List<UserDto> map(Page<UserAccount> users) {
    List<UserDto> dtos = new ArrayList<UserDto>();
    for (UserAccount user : users) {
      dtos.add(map(user));
    }
    return dtos;
  }
  
  public static List<UserDto> map(List<UserAccount> users) {
	    List<UserDto> dtos = new ArrayList<UserDto>();
	    for (UserAccount user : users) {
	      dtos.add(map(user));
	    }
	    return dtos;
	  }

  public static List<UserDto> mapUserProfile(List<UserProfile> userProfiles) {
	  List<UserDto> dtos = new ArrayList<UserDto>();
	  for (UserProfile user : userProfiles) {
		  dtos.add(mapUserProfile(user));
	  }
	  return dtos; 
  }

  public static UserDto mapUserProfile(UserProfile user) {
	    UserDto dto = new UserDto();
	    dto.setId(user.getUserId());
	    dto.setFirstName(user.getFirstName());
	    dto.setLastName(user.getLastName());
	    dto.setUsername(user.getUsername());
	    dto.setEmail(user.getEmail());
	    dto.setSrcProfileType(user.getSrcprofileType());
	    
	    return dto;
  }
}
