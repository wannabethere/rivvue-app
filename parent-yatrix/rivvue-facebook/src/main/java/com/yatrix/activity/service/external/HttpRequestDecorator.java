package com.yatrix.activity.service.external;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpRequestWrapper;

public class HttpRequestDecorator extends HttpRequestWrapper {
	
	private HttpHeaders httpHeaders;
	private boolean existingHeadersAdded;

	public HttpRequestDecorator(HttpRequest request) {
		super(request);
	}
	
	public HttpHeaders getHeaders() {
		if (!existingHeadersAdded) {
			this.httpHeaders = new HttpHeaders();
			httpHeaders.putAll(getRequest().getHeaders());
			existingHeadersAdded = true;
		}
		return httpHeaders;
	}
	

}
