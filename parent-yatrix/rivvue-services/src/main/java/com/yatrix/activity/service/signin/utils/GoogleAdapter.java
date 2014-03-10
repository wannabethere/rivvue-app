package com.yatrix.activity.service.signin.utils;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfileBuilder;

import com.yatrix.social.google.api.Google;
import com.yatrix.social.google.api.UserProfile;

public class GoogleAdapter implements ApiAdapter<Google> {

        public boolean test(Google google) {
                return true;
        }

        public void setConnectionValues(Google google, ConnectionValues values) {
                UserProfile profile = google.userOperations().getUserProfile();
                values.setProviderUserId(profile.getId());
                values.setDisplayName(profile.getName());
                values.setProfileUrl(profile.getLink());
                values.setImageUrl(profile.getPictureUrl());
        }

        public org.springframework.social.connect.UserProfile fetchUserProfile(Google google) {
                UserProfile profile = google.userOperations().getUserProfile();
                return new UserProfileBuilder()
                        .setUsername(profile.getName())
                        .setEmail(profile.getGivenName())
                        .setName(profile.getName())
                        .setFirstName(profile.getGivenName())
                        .setLastName(profile.getName())
                        .build();
        }

        public void updateStatus(Google google, String message) {
                throw new UnsupportedOperationException();
        }

}