package com.yatrix.activity.service.external;

/**
 * Enum encapsulating the differences between the various versions of the OAuth2 specification.
 * @author Keith Donald
 * @author Craig Walls
 */
public enum OAuth2Version {

	BEARER {
		public String getAuthorizationHeaderValue(String accessToken) {
			return "Bearer " + accessToken;
		}		
	},

	BEARER_DRAFT_2 {
		public String getAuthorizationHeaderValue(String accessToken) {
			return "OAuth2 " + accessToken;
		}		
	},
	
	DRAFT_10 {
		public String getAuthorizationHeaderValue(String accessToken) {
			return "OAuth " + accessToken;
		}
	},

	DRAFT_8 {
		public String getAuthorizationHeaderValue(String accessToken) {
			return "Token token=\"" + accessToken + "\"";
		}			
	};
	
	public abstract String getAuthorizationHeaderValue(String accessToken);		
}
