package com.yatrix.activity.store.mongo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.util.StringUtils;

@Document(collection = "UserAccount")
public class UserAccount extends Item implements SocialUserDetails, UserDetails{

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  @Indexed
  private String userId;
  private Role[] roles;
  private String email;
  private String displayName;
  private String imageUrl;
  private String webSite;
  private boolean accountLocked;
  private boolean trustedAccount;
  private String firstName;
  private String lastName;
  private String facebookId;
  @Column(unique = true)
  private String username;
  private String password;
  //Social Connections are used for authenticating the user.
  @Transient
  private List<UserSocialConnection> connections;
  //Avatars are the various Ids I have 
  @Transient
  private List<UserProfile> avatars;

  
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
  public void setUsername(String username) {
    this.username = username;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public UserAccount() {
    super();
  }
  @Override
  public String getUserId() {
    return this.userId;
  }

  public Role[] getRoles() {
    return roles;
  }

  public void setRoles(Role[] roles) {
    this.roles = roles;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getWebSite() {
    return webSite;
  }

  public void setWebSite(String webSite) {
    this.webSite = webSite;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    for (Role role : this.roles) {
      authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
    }
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !accountLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public boolean isAccountLocked() {
    return accountLocked;
  }

  public void setAccountLocked(boolean accountLocked) {
    this.accountLocked = accountLocked;
  }

  public boolean isTrustedAccount() {
    return trustedAccount;
  }

  public void setTrustedAccount(boolean trustedAccount) {
    this.trustedAccount = trustedAccount;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  public String getWebSiteLink() {
    if (StringUtils.hasText(getWebSite())) {
      if (getWebSite().startsWith("http://") || getWebSite().startsWith("https://")) {
        return getWebSite();
      }
      return "http://" + getWebSite();
      // add http:// to fix URL
    }
    return "#";
  }

  public String getNameLink() {
    // TODO link to profile page
    return getWebSiteLink();
  }

  public UserSocialConnection getGoogleConnection() {
    return getConnection("google");
  }

  public boolean isHasGoogleConnection() {
    return getGoogleConnection() != null;
  }

  public UserSocialConnection getTwitterConnection() {
    return getConnection("twitter");
  }

  public boolean isHasTwitterConnection() {
    return getTwitterConnection() != null;
  }

  public UserSocialConnection getFacebookConnection() {
    return getConnection("facebook");
  }

  public boolean isHasFacebookConnection() {
    return getFacebookConnection() != null;
  }

  @SuppressWarnings("hiding")
  public void updateProfile(String displayName, String email, String webSite) {
    setDisplayName(displayName);
    setEmail(email);
    setWebSite(webSite);
  }

  @Override
  public String toString() {
    String str = String.format("UserAccount{userId:'%s'; displayName:'%s';roles:[", getUserId(),
      getDisplayName());
    for (Role role : getRoles()) {
      str += role.toString() + ",";
    }
    return str + "]}";
  }
  
  public List<UserSocialConnection> getConnections(){
	  return this.connections;
  }
  
  
  // used for account social connection
  private UserSocialConnection getConnection(String providerId) {
    if (this.connections != null) {
      for (UserSocialConnection connection : this.connections) {
        if (connection.getProviderId().equals(providerId)) {
          return connection;
        }
      }
    }
    return null;
  }

  public boolean isAuthor() {
    for (Role role : getRoles()) {
      if (role == Role.ROLE_AUTHOR) {
        return true;
      }
    }
    return false;
  }

  public boolean isAdmin() {
    for (Role role : getRoles()) {
      if (role == Role.ROLE_ADMIN) {
        return true;
      }
    }
    return false;
  }
  public boolean isHasImageUrl() {
    return StringUtils.hasLength(getImageUrl());
  }
  @Override
  public String getUsername() {
	  return this.username;
  }
  public String getFacebookId() {
	  return facebookId;
  }
  public void setFacebookId(String facebookId) {
	  this.facebookId = facebookId;
  }
  public void setAvatars(List<UserProfile> avatars) {
	  this.avatars = avatars;
  }
  public List<UserProfile> getAvatars() {
	  return avatars;
  }
  
}