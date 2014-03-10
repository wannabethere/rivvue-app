package com.yatrix.activity.store.utils;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.UserIdSource;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.security.SocialUserDetailsService;

import com.yatrix.activity.store.mongo.domain.UserAccount;

/**
 * Domain Service interface for user administration. It also extends SocialUserDetailsService,
 * UserDetailsService and UserIdSource.
 * 
 * @author Sameer Mangalampalli
 *
 */
public interface UserAdminService extends SocialUserDetailsService,UserDetailsService, UserIdSource{
    
	public static final String USER_ID_PREFIX = "user";
    /**
     * Creates a new UserAccount with user social network account Connection Data.
     * Default has ROLE_USER
     * 
     * @param data
     * @return
     */
    UserAccount createUserAccount(ConnectionData data);

   
    
    /**
     * Override SocialUserDetailsService.loadUserByUserId(String userId) to 
     * return UserAccount.
     */
    UserAccount loadUserByUserId(String userId) throws UsernameNotFoundException;
    
    /**
     * Gets current logged in user. Reload UserAccount object from userId in SecurityContextHolder. 
     * 
     * @return UserAccount object from database for current user
     */
    UserAccount getCurrentUser();
    
    boolean create(UserAccount account);
    
    boolean update(UserAccount account);
    
    boolean delete(UserAccount account);

}
