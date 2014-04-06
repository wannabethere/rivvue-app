package com.yatrix.activity.service.external;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;


/**
 * ClientHttpRequestInterceptor implementation that adds the OAuth2 access token to protected resource requests before execution.
 * Copied to create custom library for supporting multiple http end points.
 * @author Keith Donald
 * @author Craig Walls
 */
public class OAuth2RequestInterceptor implements ClientHttpRequestInterceptor {

	private final String accessToken;
	
	private final OAuth2Version oauth2Version;

	public OAuth2RequestInterceptor(String accessToken, OAuth2Version oauth2Version) {
		this.accessToken = accessToken;
		this.oauth2Version = oauth2Version;
	}
	
	public ClientHttpResponse intercept(final HttpRequest request, final byte[] body, ClientHttpRequestExecution execution) throws IOException {
		HttpRequest protectedResourceRequest = new HttpRequestDecorator(request);
		protectedResourceRequest.getHeaders().set("Authorization", oauth2Version.getAuthorizationHeaderValue(accessToken));
		return execution.execute(protectedResourceRequest, body);
	}

}