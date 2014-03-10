package com.yatrix.social.google.api.impl.json;


import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.yatrix.social.google.api.CalendarProfile;
import com.yatrix.social.google.api.EventProfile;
import com.yatrix.social.google.api.GoogleEventAttendees;
import com.yatrix.social.google.api.GoogleEventDate;
import com.yatrix.social.google.api.GoogleOverrides;
import com.yatrix.social.google.api.GoogleReminders;
import com.yatrix.social.google.api.UserProfile;

public class GoogleModule extends SimpleModule{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5332969845162282039L;

	public GoogleModule() {
	    super("GoogleModule", new Version(1, 0, 0, "alpha-1", null, null));
	  }
	 
	  @Override
	  public void setupModule(final SetupContext context) {
	    context.setMixInAnnotations(UserProfile.class, UserProfileMixin.class);
	    context.setMixInAnnotations(EventProfile.class, EventProfileMixin.class);
	    context.setMixInAnnotations(CalendarProfile.class, CalendarProfileMixin.class);
	    context.setMixInAnnotations(GoogleEventDate.class, GoogleEventDateMixin.class);
	    context.setMixInAnnotations(GoogleEventAttendees.class, GoogleEventAttendeesMixin.class);
	    context.setMixInAnnotations(GoogleReminders.class, GoogleRemindersMixin.class);
	    context.setMixInAnnotations(GoogleOverrides.class, GoogleOverridesMixin.class);
	  }

}
