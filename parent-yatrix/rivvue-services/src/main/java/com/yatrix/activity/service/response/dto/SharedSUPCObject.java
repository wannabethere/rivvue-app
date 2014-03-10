package com.yatrix.activity.service.response.dto;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.yatrix.activity.service.utils.SUPCGenerator;



public class SharedSUPCObject {

	
	private String title;
	private String url;
	private String description;
	private String supccode;
	private String image;
	
	public SharedSUPCObject(){
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSupccode() {
		return supccode;
	}
	public void setSupccode(String supccode) {
		this.supccode = supccode;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public static void main(String[] args){
		ObjectMapper mapper=new ObjectMapper();
		SharedSUPCObject p = new SharedSUPCObject();
		p.setDescription("Description");
		p.setTitle("title");
		try {
			p.setSupccode(SUPCGenerator.encrypt(12232, SUPCGenerator.getNextRandom()));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			p.setSupccode("DUMMY");
		}
		p.setUrl("http://dysdfsdf-sfsdfd23232.com");
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
