package com.yatrix.activity.service.external.util;

import static com.yatrix.activity.service.external.util.StringUtils.isBlank;
import java.util.ArrayList;
import java.util.List;


public class PagedList<T> extends ArrayList<T> {
	private static final long serialVersionUID = 1L;
	private final PagingParameters previousPage;
	private final PagingParameters nextPage;
	private final String previousPageUrl;
	private final String nextPageUrl;

	/**
	 * Support iteration via URLS or paging parameters of offset limit.
	 * @param unpagedList
	 * @param previousPage
	 *    Paging Parameters = null.
	 * @param nextPage
	 *    Paging Parameters = null
	 * @param previousPageUrl
	 *    url with Https/http
	 * @param nextPageUrl
	 *    url with https/http.   
	 */
	public PagedList(List<T> unpagedList, String previousPageUrl, String nextPageUrl) {
		super(unpagedList);
		this.previousPageUrl = previousPageUrl;
		this.nextPageUrl = nextPageUrl;
		this.nextPage=null;
		this.previousPage=null;
	}
	
	/**
	 * Support iteration via URLS or paging parameters of offset limit.
	 * @param unpagedList
	 * @param previousPage
	 *    Paging Parameters.
	 * @param nextPage
	 *    Paging Parameters.
	 * @param previousPageUrl
	 *    ==null
	 * @param nextPageUrl
	 *    == null   
	 */
	public PagedList(List<T> unpagedList, PagingParameters previousPage, PagingParameters nextPage) {
		super(unpagedList);
		this.previousPage = previousPage;
		this.nextPage = nextPage;
		this.nextPageUrl=null;
		this.previousPageUrl=null;
	}
	
	public PagingParameters getPreviousPage() {
		return previousPage;
	}

	public PagingParameters getNextPage() {
		return nextPage;
	}


	/**
	 * Does this connection have a previous page of data?
	 * 
	 * @return {@code true} if there is a previous page of data for this connection, {@code false} otherwise.
	 */
	public boolean hasPrevious() {
		return !isBlank(getPreviousPageUrl());
	}

	/**
	 * Does this connection have a next page of data?
	 * 
	 * @return {@code true} if there is a next page of data for this connection, {@code false} otherwise.
	 */
	public boolean hasNext() {
		return !isBlank(getNextPageUrl());
	}

	public String getPreviousPageUrl() {
		return previousPageUrl;
	}

	
	public String getNextPageUrl() {
		return nextPageUrl;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ReflectionUtils.toString(this);
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {
		return ReflectionUtils.equals(this, object);
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return ReflectionUtils.hashCode(this);
	}


}