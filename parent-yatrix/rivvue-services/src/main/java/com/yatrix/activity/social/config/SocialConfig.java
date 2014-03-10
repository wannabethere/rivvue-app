package com.yatrix.activity.social.config;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.facebook.web.DisconnectController;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SocialAuthenticationProvider;
import org.springframework.social.security.SocialAuthenticationServiceLocator;
import org.springframework.social.security.SocialAuthenticationServiceRegistry;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import com.yatrix.activity.store.mongo.repository.AutoConnectionSignUp;
import com.yatrix.activity.store.mongo.repository.ConnectionConverter;
import com.yatrix.activity.store.mongo.repository.ConnectionService;
import com.yatrix.activity.store.mongo.repository.MongoPersistentTokenRepositoryImpl;
import com.yatrix.activity.store.mongo.repository.MongoUsersConnectionRepository;
import com.yatrix.activity.store.mongo.repository.RememberMeTokenRepository;
import com.yatrix.activity.store.mongo.repository.UserAccountRepository;
import com.yatrix.activity.store.mongo.repository.UserSocialConnectionService;
import com.yatrix.activity.store.mongo.service.impl.ProfileService;
import com.yatrix.activity.service.signin.utils.GoogleConnectionFactory;
import com.yatrix.activity.service.signin.utils.SimpleSignInAdapter;
import com.yatrix.activity.store.utils.ActivityFeedProcessor;
import com.yatrix.activity.store.utils.CounterService;
import com.yatrix.activity.service.utils.FacebookConnectInterceptor;
import com.yatrix.activity.service.utils.FacebookSignInInterceptor;
import com.yatrix.activity.store.utils.UserAdminService;

@Configuration
public class SocialConfig {

	@Autowired 
	private Environment env;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Inject
	private RememberMeTokenRepository rememberMeTokenRepository;

	@Inject 
	private UserAdminService userAdminService;

	@Inject
	private CounterService counterService;

	@Inject 
	private ProfileService profileService;
	
	@Autowired
	private UserAccountRepository userAccountRepository;
	
	@Bean
	public RestTemplate initializeRestTemplate(){
		return new RestTemplate();
	}


	@Bean
	public SocialAuthenticationServiceLocator connectionFactoryLocator() {

		SocialAuthenticationServiceRegistry socialregistry= new SocialAuthenticationServiceRegistry();
		socialregistry.addConnectionFactory(new FacebookConnectionFactory(
				env.getRequiredProperty("facebook.clientId"),
				env.getRequiredProperty("facebook.clientSecret")));
		
		//ocialregistry= new SocialAuthenticationServiceRegistry();
		socialregistry.addConnectionFactory(new GoogleConnectionFactory(
				env.getRequiredProperty("google.clientId"),
				env.getRequiredProperty("google.clientSecret")));

		/* registry.addConnectionFactory(new TwitterConnectionFactory(
        		env.getRequiredProperty("twitter.consumerKey"),
        		env.getRequiredProperty("twitter.consumerSecret")));*/
		return socialregistry;
	}	

	@Bean
	public TextEncryptor textEncryptor() {
		return Encryptors.noOpText();
	}

	@Bean
	public  ConnectionConverter connectionConverter() {
		return new ConnectionConverter();
	}

	@Bean
	public ActivityFeedProcessor initializeActivities(){
		ActivityFeedProcessor afp=new ActivityFeedProcessor(mongoTemplate, counterService);
		afp.init();
		return afp;
	}


	@Bean
	public ConnectionService userSocialConnectionService(){
		return new UserSocialConnectionService(mongoTemplate, connectionConverter());
	}


	@Bean
	public UsersConnectionRepository usersConnectionRepository() {
		MongoUsersConnectionRepository repository= new MongoUsersConnectionRepository(userSocialConnectionService(), connectionFactoryLocator(), textEncryptor());	
		repository.setConnectionSignUp(new AutoConnectionSignUp(userAdminService));
		return repository;
	}



	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
	public ConnectionRepository connectionRepository() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null)
			throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");

		return usersConnectionRepository().createConnectionRepository(authentication.getName());
	}

	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)	
	public Facebook facebook() {
		Connection<Facebook> facebook = connectionRepository().findPrimaryConnection(Facebook.class);
		return facebook != null ? facebook.getApi() : new FacebookTemplate();
	}
	
	@Bean
	public ProviderSignInController providerSignInController(RequestCache requestCache) {
		ProviderSignInController providerSignInController = new ProviderSignInController(connectionFactoryLocator(), usersConnectionRepository(), new SimpleSignInAdapter(requestCache,userAdminService));	
		providerSignInController.addSignInInterceptor(new FacebookSignInInterceptor(profileService, userAccountRepository));
		return providerSignInController;
	}

	@Bean
	public DisconnectController disconnectController() {
		return new DisconnectController(usersConnectionRepository(), env.getProperty("facebook.clientSecret"));
	}


	@Bean
	public ConnectController connectController() {
		ConnectController controller = new ConnectController(connectionFactoryLocator(), connectionRepository());
		controller.setApplicationUrl(env.getRequiredProperty("application.url"));
		controller.addInterceptor(new FacebookConnectInterceptor(profileService, userAccountRepository)); 
		return controller;
	}

	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
		HiddenHttpMethodFilter filter = new HiddenHttpMethodFilter();
		return filter;
	}

	//Remember Token Repository
	@Bean
	public RememberMeServices rememberMeServices(){
		PersistentTokenBasedRememberMeServices rememberMeServices = new PersistentTokenBasedRememberMeServices(
				env.getProperty("application.key"), userAdminService, persistentTokenRepository());
		rememberMeServices.setAlwaysRemember(true);
		return rememberMeServices;
	}

	@Bean 
	public RememberMeAuthenticationProvider rememberMeAuthenticationProvider(){
		RememberMeAuthenticationProvider rememberMeAuthenticationProvider = 
				new RememberMeAuthenticationProvider(env.getProperty("application.key"));
		return rememberMeAuthenticationProvider; 
	}

	@Bean 
	public PersistentTokenRepository persistentTokenRepository() {
		return new MongoPersistentTokenRepositoryImpl(rememberMeTokenRepository);
	}

	@Bean 
	public SocialAuthenticationFilter socialAuthenticationFilter(AuthenticationManager authenticationManager,  SocialAuthenticationServiceLocator authenticationServiceLocator) {
		SocialAuthenticationFilter socialAuthenticationFilter = new SocialAuthenticationFilter(authenticationManager, userAdminService, usersConnectionRepository(), authenticationServiceLocator);
		//socialAuthenticationFilter.setSignupUrl("/store/signup"); // TODO: Fix filter to handle in-app paths
		socialAuthenticationFilter.setRememberMeServices(rememberMeServices());
		return socialAuthenticationFilter;
	}

	@Bean
	public AuthenticationProvider socialAuthenticationProvider() {
		return new SocialAuthenticationProvider(usersConnectionRepository(), userAdminService);
	}
}