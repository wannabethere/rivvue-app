package com.yatrix.activity.store.mongo.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import javax.inject.Inject;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.ConnectionData;
import org.springframework.stereotype.Service;


import com.yatrix.activity.store.mongo.domain.Role;
import com.yatrix.activity.store.mongo.domain.UserAccount;
import com.yatrix.activity.store.utils.AccountUtils;
import com.yatrix.activity.store.utils.CounterService;
import com.yatrix.activity.store.utils.UserAdminService;

/**
 * Implementation for UserAdminService.
 * 
 * @author Sameer Mangalampalli
 * 
 */
@Service
public class UserAdminServiceImpl implements UserAdminService {
    
    
    private UserAccount user;

    private final UserAccountRepository accountRepository;
    private final CounterService counterService;

    @Inject
    public UserAdminServiceImpl(UserAccountRepository accountRepository, CounterService counterService) {
        this.accountRepository = accountRepository;
        this.counterService = counterService;
    }

    public UserAccount createUserAccount(ConnectionData data) {
        long userIdSequence = this.counterService.getNextUserIdSequence();
        UserAccount account = new UserAccount();
        account.setUserId(USER_ID_PREFIX + userIdSequence);
        account.setDisplayName(data.getDisplayName());
        account.setImageUrl(data.getImageUrl());
        account.setUsername(data.getProviderUserId());
        account.setTrustedAccount(true);
        
        if (userIdSequence == 1l) {
            account.setRoles(new Role[] { Role.ROLE_USER, Role.ROLE_AUTHOR,
                    Role.ROLE_ADMIN });
        } else {
            account.setRoles(new Role[] { Role.ROLE_USER });
        }
        this.accountRepository.save(account);

        return account;
    }

   
    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.social.security.SocialUserDetailsService#loadUserByUserId(java.lang.String)
     */
    
    public UserAccount loadUserByUserId(String userId) throws UsernameNotFoundException {
        UserAccount account = accountRepository.findByUserId(userId);
        if (account == null) {
            throw new UsernameNotFoundException("Cannot find user by userId " + userId);
        }
        this.user=account;
        return account;
    }

    
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	 UserAccount account = accountRepository.findByUsername(username);
         if (account == null) {
             throw new UsernameNotFoundException("Cannot find user by username " + username);
         }
         this.user=account;
         return account;
    }
    
    
    /**
	 * Retrieves a collection of {@link GrantedAuthority} based on a numerical role.
	 * 
	 * @param role the numerical role
	 * @return a collection of {@link GrantedAuthority
	 */
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles());
		return authList;
	}
	
	
	
	
	/**
	 * Converts a numerical role to an equivalent list of roles.
	 * 
	 * @param role the numerical role
	 * @return list of roles as as a list of {@link String}
	 */
	public List<String> getRoles() {
		List<String> roles = new ArrayList<String>();
		
		for(Role role:this.user.getRoles()){
			roles.add(role.getAuthority());
		}
		
		return roles;
	}
	
	/**
	 * Wraps {@link String} roles to {@link SimpleGrantedAuthority} objects.
	 * 
	 * @param roles {@link String} of roles
	 * @return list of granted authorities
	 */
	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.social.UserIdSource#getUserId()
     */
    
    public String getUserId() {
    	System.out.println("LOGGED IN USER: "+ AccountUtils.getLoginUserId());
        return AccountUtils.getLoginUserId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jiwhiz.blog.domain.account.UserAdminService#getCurrentUser()
     */
    public UserAccount getCurrentUser() {
        return accountRepository.findByUserId(getUserId());
    }

	@Override
	public boolean create(UserAccount account) {
		UserAccount existingUser = accountRepository.findByUsername(account.getUsername());
		if (existingUser != null) {
			return false;
		}
		UserAccount saved = accountRepository.save(account);
		if (saved == null){ 
			return false;
		}
		
		return true;
		
	}

	@Override
	public boolean update(UserAccount account) {
		UserAccount existingUser = accountRepository.findByUsername(account.getUsername());
		if (existingUser == null){ 
			return false;
		}
		
		// Only firstName, lastName, and role fields are updatable
		existingUser.setFirstName(account.getFirstName());
		existingUser.setLastName(account.getLastName());
		existingUser.setRoles(account.getRoles());
		
		UserAccount saved = accountRepository.save(existingUser);
		if (saved == null) {
			return false;
		}
			
		
		return true;
	}

	@Override
	public boolean delete(UserAccount account) {
		UserAccount existingUser = accountRepository.findOne(account.getId());
		if (existingUser == null){ 
			return false;
		}
		
		accountRepository.delete(existingUser);
		UserAccount deletedUser = accountRepository.findOne(account.getId());
		if (deletedUser != null){ 
			return false;
		}
		
		return true;
	}

	
}
