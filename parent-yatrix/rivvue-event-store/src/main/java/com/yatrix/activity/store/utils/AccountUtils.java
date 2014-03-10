package com.yatrix.activity.store.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.yatrix.activity.store.mongo.domain.UserAccount;

/**
 * 
 * @author Sameer Mangalampalli
 * 
 */
@Service("authenticationService")
public class AccountUtils {
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static UserAccount getLoginUserAccount() {
        if (getAuthentication() != null && getAuthentication().getPrincipal() instanceof UserAccount) {
        	System.out.println("Returning User Logged In");
            return (UserAccount)getAuthentication().getPrincipal();
        }
        return null;
    }

    public static String getLoginUserId() {
        UserAccount account = getLoginUserAccount();
        return (account == null) ? null : account.getUserId();
    }

    private AccountUtils() {}
}
