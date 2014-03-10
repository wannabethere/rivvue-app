package com.yatrix.activity.service.response.dto;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * privacy={'value': 'CUSTOM', 'allow': '{friend-list-id}'}
 * @author tkmald2
 *
 */
public class PrivacyParameters {

	private String[] allow;
	private String value;
	
	public PrivacyParameters(String[] allow){
		this.allow=allow;
		this.value="CUSTOM";
	}
	
	
	public String[] getAllow() {
		return allow;
	}


	public String getValue() {
		return value;
	}


	public static void main(String[] args){
		ObjectMapper mapper=new ObjectMapper();
		PrivacyParameters p = new PrivacyParameters(new String[]{"23123213","23123213213"});
		try {
			System.out.println(mapper.writeValueAsString(p));
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
