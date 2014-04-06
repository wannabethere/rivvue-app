package com.yatrix.activity.service.external.util;

/**
 * Carries parameters to describe a paged set of results.
 * @author Craig Walls
 */
public class PagingParameters {
	
	private final Integer limit;
	private final Integer offset;
	private final Long since;
	private final Long until;
	private final String url;

	/**
	 * Constructs a PagedListParameters.
	 * @param limit The number of items to limit the list to.
	 * @param offset The offset into the full result list to start this list at.
	 * @param since The beginning timestamp bound for time-sensitive content (e.g., posts, comments, etc).
	 * @param until The ending timestamp bound for time-sensitive content (e.g., posts, comments, etc).
	 */
	public PagingParameters(Integer limit, Integer offset, Long since, Long until,String url) {
		this.limit = limit;
		this.offset = offset;
		this.since = since;
		this.until = until;
		this.url= url;
	}

	public Integer getLimit() {
		return limit;
	}

	public Integer getOffset() {
		return offset;
	}
	
	public Long getSince() {
		return since;
	}

	public Long getUntil() {
		return until;
	}

	public String getUrl() {
		return url;
	}
	
}