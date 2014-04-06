package com.yatrix.activity.service.external;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Properties;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpProtocolParams;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.ClassUtils;

public class ClientHttpRequestFactorySelector {
	public static ClientHttpRequestFactory foo() {
		return new HttpComponentsClientHttpRequestFactory();
	}
	
	public static ClientHttpRequestFactory getRequestFactory() {
		Properties properties = System.getProperties();
		String proxyHost = properties.getProperty("http.proxyHost");
		int proxyPort = properties.containsKey("http.proxyPort") ? Integer.valueOf(properties.getProperty("http.proxyPort")) : 80;
		if (HTTP_COMPONENTS_AVAILABLE) {
			return HttpComponentsClientRequestFactoryCreator.createRequestFactory(proxyHost, proxyPort);
		} else {
			SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
			if (proxyHost != null) {
				requestFactory.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort)));
			}
			return requestFactory;
		}
	}
	
	/**
	 * Decorates a request factory to buffer responses so that the responses may be repeatedly read.
	 * @param requestFactory the request factory to be decorated for buffering
	 * @return a buffering request factory
	 */
	public static ClientHttpRequestFactory bufferRequests(ClientHttpRequestFactory requestFactory) {
		return new BufferingClientHttpRequestFactory(requestFactory);
	}
	
	private static final boolean HTTP_COMPONENTS_AVAILABLE = ClassUtils.isPresent("org.apache.http.client.HttpClient", ClientHttpRequestFactory.class.getClassLoader());

	public static class HttpComponentsClientRequestFactoryCreator {
		
		public static ClientHttpRequestFactory createRequestFactory(String proxyHost, int proxyPort) {
			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory() {
				@Override
				protected void postProcessHttpRequest(HttpUriRequest request) {
					HttpProtocolParams.setUseExpectContinue(request.getParams(), false);				}
			};
			if (proxyHost != null) {
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpHost proxy = new HttpHost(proxyHost, proxyPort);
				httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
				requestFactory.setHttpClient(httpClient);
			}
			return requestFactory;			
		}
	}
}
