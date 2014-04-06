package com.yatrix.activity.service.fb.rest;

import static com.yatrix.activity.service.external.util.UrlUtils.extractParametersFromQueryString;
import static java.lang.String.format;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yatrix.activity.service.external.util.Connection;
import com.yatrix.activity.service.external.util.PagedList;



import com.yatrix.activity.service.beans.Parameter;
import com.yatrix.activity.service.external.util.ReflectionUtils;
import com.yatrix.activity.service.fb.types.BatchRequest;
import com.yatrix.activity.service.fb.types.BatchResponse;

public interface IFacebookClient {

	
	  /**
	   * Fetches a single <a href="http://developers.facebook.com/docs/reference/api/">Graph API object</a>, mapping the
	   * result to an instance of {@code objectType}.
	   * 
	   * @param <T>
	   *          Java type to map to.
	   * @param object
	   *          ID of the object to fetch, e.g. {@code "me"}.
	   * @param objectType
	   *          Object type token.
	   * @param parameters
	   *          URL parameters to include in the API call (optional).
	   * @return An instance of type {@code objectType} which contains the requested object's data.
	   * @throws FacebookException
	   *           If an error occurs while performing the API call.
	   */
	  <T> T fetchObject(String object, Class<T> objectType, Parameter... parameters);
	  
	  /**
	   * Fetches multiple <a href="http://developers.facebook.com/docs/reference/api/">Graph API objects</a> in a single
	   * call, mapping the results to an instance of {@code objectType}.
	   * <p>
	   * You'll need to write your own container type ({@code objectType}) to hold the results. See <a
	   * href="http://restfb.com">http://restfb.com</a> for an example of how to do this.
	   * 
	   * @param <T>
	   *          Java type to map to.
	   * @param ids
	   *          IDs of the objects to fetch, e.g. {@code "me", "arjun"}.
	   * @param objectType
	   *          Object type token.
	   * @param parameters
	   *          URL parameters to include in the API call (optional).
	   * @return An instance of type {@code objectType} which contains the requested objects' data.
	   * @throws FacebookException
	   *           If an error occurs while performing the API call.
	   */
	  <T> T fetchObjects(List<String> ids, Class<T> objectType, Parameter... parameters);
	  
	  
	  /**
	   * 
	   * @param <T>
	   *          Java type to map to.
	   * @param ids
	   *          IDs of the objects to fetch, e.g. {@code "me", "arjun"}.
	   * @param connnectionType
	   *          Object type token.
	   * @param parameters
	   *          URL parameters to include in the API call (optional).
	   * @param fields
	   * @return
	   * 	returns a paged list of connections
	   */
	   <T> Connection<T> fetchConnections(String objectId, String connectionType, Class<T> type, Parameter... parameters);
	   
	   /**
	    * Fetches a previous/next page of a Graph API {@code Connection} type, mapping the result to an instance of
	    * {@code connectionType}.
	    * 
	    * @param <T>
	    *          Java type to map to.
	    * @param connectionPageUrl
	    *          The URL of the connection page to fetch, usually retrieved via {@link Connection#getPreviousPageUrl()} or
	    *          {@link Connection#getNextPageUrl()}.
	    * @param connectionType
	    *          Connection type token.
	    * @return An instance of type {@code connectionType} which contains the requested Connection's data.
	    * @throws FacebookException
	    *           If an error occurs while performing the API call.
	    */
	   <T> Connection<T> fetchConnectionsPage(String pageUrl,   Class<T> type);
	   
	  
	  /**
	   * Executes operations as a batch using the <a href="https://developers.facebook.com/docs/reference/api/batch/">Batch
	   * API</a>.
	   * 
	   * @param batchRequests
	   *          The operations to execute.
	   * @return The execution results in the order in which the requests were specified.
	   */
	  List<BatchResponse> executeBatch(BatchRequest... batchRequests);
	  
	 
	  

	  /**
	   * Executes operations as a batch using the <a href="https://developers.facebook.com/docs/reference/api/batch/">Batch
	   * API</a>.
	   * 
	   * @param batchRequests
	   *          The operations to execute.
	   * @return The execution results in the order in which the requests were specified.
	   */
	  List<BatchResponse> executeBatch(List<BatchRequest> batchRequests);
	  
	  /**
	   * Executes operations as a batch with binary attachments using the <a
	   * href="https://developers.facebook.com/docs/reference/api/batch/">Batch API</a>.
	   * 
	   * @param batchRequests
	   *          The operations to execute.
	   * @param binaryAttachments
	   *          Binary attachments referenced by the batch requests.
	   * @return The execution results in the order in which the requests were specified.
	   * @since 1.6.5
	  TODO:V2 
	  List<BatchResponse> executeBatch(List<BatchRequest> batchRequests, List<BinaryAttachment> binaryAttachments);
    	*/
	  
		
	  /**
	   * Performs a <a href="http://developers.facebook.com/docs/api#publishing">Graph API publish</a> operation on the
	   * given {@code connection}, mapping the result to an instance of {@code objectType}.
	   * 
	   * @param <T>
	   *          Java type to map to.
	   * @param connection
	   *          The Connection to publish to.
	   * @param objectType
	   *          Object type token.
	   * @param parameters
	   *          URL parameters to include in the API call.
	   * @return An instance of type {@code objectType} which contains the Facebook response to your publish request.
	   * @throws FacebookException
	   *           If an error occurs while performing the API call.
	   */
	  <T> T publish(String connection, Class<T> objectType, Parameter... parameters);
	  
	  /**
	   * Performs a <a href="http://developers.facebook.com/docs/api#publishing">Graph API publish</a> operation on the
	   * given {@code connection} and includes a file - a photo, for example - in the publish request, and mapping the
	   * result to an instance of {@code objectType}.
	   * 
	   * @param <T>
	   *          Java type to map to.
	   * @param connection
	   *          The Connection to publish to.
	   * @param objectType
	   *          Object type token.
	   * @param binaryAttachment
	   *          The file to include in the publish request.
	   * @param parameters
	   *          URL parameters to include in the API call.
	   * @return An instance of type {@code objectType} which contains the Facebook response to your publish request.
	   * @throws FacebookException
	   *           If an error occurs while performing the API call.
	  TODO:V2
	  <T> T publish(String connection, Class<T> objectType, BinaryAttachment binaryAttachment, Parameter... parameters);
	   */
	  /**
	   * Performs a <a href="http://developers.facebook.com/docs/api#publishing">Graph API publish</a> operation on the
	   * given {@code connection} and includes a file - a photo, for example - in the publish request, and mapping the
	   * result to an instance of {@code objectType}.
	   * 
	   * @param <T>
	   *          Java type to map to.
	   * @param connection
	   *          The Connection to publish to.
	   * @param objectType
	   *          Object type token.
	   * @param parameters
	   *          URL parameters to include in the API call.
	   * @return An instance of type {@code objectType} which contains the Facebook response to your publish request.
	   * @throws FacebookException
	   *           If an error occurs while performing the API call.
	   */
	  
	  <T> T delete(String objectId, Parameter...parameters);
	  
	  
	  
	  AccessToken obtainAppAccessToken(String appId, String appSecret);
	  
	  /**
	   * Represents an access token/expiration date pair.
	   * <p>
	   * Facebook returns these types when performing access token-related operations - see
	   * {@link com.yatrix.activity.service.fb.rest.IFacebookClient#convertSessionKeysToAccessTokens(String, String, String...)},
	   * {@link com.yatrix.activity.service.fb.rest.IFacebookClient#obtainAppAccessToken(String, String)}, and
	   * {@link com.yatrix.activity.service.fb.rest.IFacebookClient#obtainExtendedAccessToken(String, String, String)} for details.
	   * 
	   * @author <a href="http://restfb.com">Mark Allen</a>
	   */
	  public static class AccessToken {
	    @Facebook("access_token")
	    private String accessToken;

	    @Facebook
	    private Long expires;

	    /**
	     * Given a query string of the form {@code access_token=XXX} or {@code access_token=XXX&expires=YYY}, return an
	     * {@code AccessToken} instance.
	     * <p>
	     * The {@code queryString} is required to contain an {@code access_token} parameter with a non-{@code null} value.
	     * The {@code expires} value is optional and should be the number of seconds since the epoch. If the {@code expires}
	     * value cannot be parsed, the returned {@code AccessToken} will have a {@code null} {@code expires} value.
	     * 
	     * @param queryString
	     *          The Facebook query string out of which to parse an {@code AccessToken} instance.
	     * @return An {@code AccessToken} instance which corresponds to the given {@code queryString}.
	     * @throws IllegalArgumentException
	     *           If no {@code access_token} parameter is present in the query string.
	     * @since 1.6.10
	     */
	    public static AccessToken fromQueryString(String queryString) {
	      // Query string can be of the form 'access_token=XXX' or
	      // 'access_token=XXX&expires=YYY'
	      Map<String, List<String>> urlParameters = extractParametersFromQueryString(queryString);

	      String extendedAccessToken = null;

	      if (urlParameters.containsKey("access_token"))
	        extendedAccessToken = urlParameters.get("access_token").get(0);

	      if (extendedAccessToken == null)
	        throw new IllegalArgumentException(format(
	          "Was expecting a query string of the form 'access_token=XXX' or 'access_token=XXX&expires=YYY'. "
	              + "Instead, the query string was '%s'", queryString));

	      Long expires = null;

	      // If an expires value was provided and it's a valid long, great - use it.
	      // Otherwise ignore it.
	      if (urlParameters.containsKey("expires")) {
	        try {
	          expires = Long.valueOf(urlParameters.get("expires").get(0));
	        } catch (NumberFormatException e) {}
	        if (expires != null)
	          expires = new Date().getTime() + 1000L * expires;
	      }

	      AccessToken accessToken = new AccessToken();
	      accessToken.accessToken = extendedAccessToken;
	      accessToken.expires = expires;
	      return accessToken;
	    }

	    /**
	     * @see java.lang.Object#hashCode()
	     */
	    @Override
	    public int hashCode() {
	      return ReflectionUtils.hashCode(this);
	    }

	    /**
	     * @see java.lang.Object#equals(java.lang.Object)
	     */
	    @Override
	    public boolean equals(Object that) {
	      return ReflectionUtils.equals(this, that);
	    }

	    /**
	     * @see java.lang.Object#toString()
	     */
	    @Override
	    public String toString() {
	      return ReflectionUtils.toString(this);
	    }

	    /**
	     * The access token's value.
	     * 
	     * @return The access token's value.
	     */
	    public String getAccessToken() {
	      return accessToken;
	    }

	    /**
	     * The date on which the access token expires.
	     * 
	     * @return The date on which the access token expires.
	     */
	    public Date getExpires() {
	      return expires == null ? null : new Date(expires);
	    }
	  }

	
	
}
