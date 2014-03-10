package com.yatrix.activity.store.mongo.repository;

import com.yatrix.activity.store.mongo.domain.Role;
import com.yatrix.activity.store.mongo.domain.UserAccount;
import com.yatrix.activity.store.utils.CounterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SimpleSocialUserDetailsService
  implements SocialUserDetailsService, UserDetailsService
{

  @Autowired
  private UserAccountRepository userAccountRepository;

  @Autowired
  private CounterService counterService;

  public SimpleSocialUserDetailsService() {
  }

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    return loadUserByUserId(userName);
  }

  public UserAccount createUserAccount(ConnectionData data) {
    UserAccount account = new UserAccount();
    account.setUserId(this.counterService.getNextUserIdSequence() + "");
    account.setDisplayName(data.getDisplayName());
    account.setImageUrl(data.getImageUrl());
    account.setRoles(new Role[] { Role.ROLE_USER });
    this.userAccountRepository.save(account);
    return account;
  }

  @Override
  public SocialUserDetails loadUserByUserId(String userId)
    throws UsernameNotFoundException, DataAccessException {
    UserAccount account = this.userAccountRepository.findByUserId(userId);
    if (account == null) {
      throw new UsernameNotFoundException("Cannot find user by userId " + userId);
    }

    return account;
  }

}
