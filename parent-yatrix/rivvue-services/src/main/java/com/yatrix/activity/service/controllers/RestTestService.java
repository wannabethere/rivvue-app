package com.yatrix.activity.service.controllers;

import com.yatrix.activity.ext.domain.google.PlacesSearchResults;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.client.RestTemplate;

public class RestTestService {

  public final static String dummyAccessToken = "CAAIlWa556u8BAP1O1bcsUonbt89hZBmEivNAb0RiekdQn7B1eMcP5ycdn402hzvOmfBdzRLPV980MryuxKnpYOAIUGZBCpsMQgODnnnDNYjlEj8UhW5fl79MZB5339t1oyNujolQg3uWAcZBkNPN";

  public static final String PLACE_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?query={query}&sensor=false&key={key}";

  public static void main(String[] args) {

    /*
     * FacebookTemplate fb=new FacebookTemplate(dummyAccessToken, "meandkohlsshare"); ObjectMapper
     * mapper=new ObjectMapper(); SharedSUPCObject p = new SharedSUPCObject(); try {
     * p.setDescription("Description"); p.setTitle("title");
     * p.setSupccode(SUPCGenerator.encrypt(12232, SUPCGenerator.getNextRandom()));
     * p.setUrl("http://dry-everglades-5630.herokuapp.com/"); MultiValueMap<String,Object> data=new
     * LinkedMultiValueMap<String,Object>(); data.add("object", mapper.writeValueAsString(p));
     * String responseId= fb.publish("me/objects", "meandkohlsshare:coupon", data);
     * System.out.println("ID:" + responseId); MultiValueMap<String,Object> data1=new
     * LinkedMultiValueMap<String,Object>(); //PrivacyParameters priv = new PrivacyParameters(new
     * String[]{"602983700"}); //data.add("privacy", mapper.writeValueAsString(priv));
     * data1.add("tags", "602983700"); data1.add("message",
     * "Sameer Has Shared with you the SUPC Code:"+p.getSupccode()); data1.add("coupon",responseId);
     * String responseId1= fb.publish("me", "meandkohlsshare:supccouponcode", data1);
     * System.out.println("ID:" + responseId1); } catch (JsonGenerationException e) { // TODO
     * Auto-generated catch block e.printStackTrace(); } catch (JsonMappingException e) { // TODO
     * Auto-generated catch block e.printStackTrace(); } catch (IOException e) { // TODO
     * Auto-generated catch block e.printStackTrace(); } catch (Exception e1) { // TODO
     * Auto-generated catch block p.setSupccode("DUMMY"); }
     */

    @SuppressWarnings("unused")
    ObjectMapper mapper = new ObjectMapper();
    RestTemplate restTemplate = new RestTemplate();
    String results = restTemplate.getForObject(PLACE_SEARCH_URL, String.class,
      "badminton in SunnyVale", "AIzaSyCxvCnxIHYcXxw0QvhVzP9fkIVIvBz1nZI");
    System.out.println(results);

    PlacesSearchResults results1 = restTemplate.getForObject(PLACE_SEARCH_URL,
      PlacesSearchResults.class, "badminton in SunnyVale",
      "AIzaSyCxvCnxIHYcXxw0QvhVzP9fkIVIvBz1nZI");
    System.out.println(results1);

  }
}
