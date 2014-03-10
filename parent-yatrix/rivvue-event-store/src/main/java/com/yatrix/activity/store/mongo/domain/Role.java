package com.yatrix.activity.store.mongo.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role
  implements GrantedAuthority {
  ROLE_ADMIN, // can manage user account, all posts
  ROLE_AUTHOR, // can manage own posts
  ROLE_USER, // can edit own comment, can edit own profile
  ANONYMOUS;

  @Override
  public String getAuthority() {
    return this.toString();
  }
}
