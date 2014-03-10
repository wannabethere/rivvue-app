package com.yatrix.activity.service.controllers;

import com.yatrix.activity.service.response.dto.SharedData;
import com.yatrix.activity.service.response.dto.SharedSUPCObject;
import com.yatrix.activity.service.utils.SUPCGenerator;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.OperationNotPermittedException;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/fb")
public class FacebookController {

  @Autowired
  private ConnectionRepository connectionRepository;

  @ModelAttribute("source")
  public String source() {
    return "fb";
  }

  @RequestMapping(
      value = "/profile")
  public String getProfile(ModelMap model) {
    try {
      Facebook facebook = connectionRepository.getPrimaryConnection(Facebook.class).getApi();
      model.addAttribute("profileLink", facebook.userOperations().getUserProfile().getLink());
      model.addAttribute("profileInfo", facebook.userOperations().getUserProfile());
      model.addAttribute("authname", SecurityContextHolder.getContext().getAuthentication()
        .getName());
      System.out.println("\n");
      System.out.println("\n");
      System.out.println("\n");
      System.out.println("\n");
      System.out.println("\n");
      return "facebook/profile";
    } catch (NotConnectedException e) {
        model.addAttribute("authname", SecurityContextHolder.getContext().getAuthentication()
                .getName());
      return "facebook/connect";
    }
  }

  @RequestMapping(
      value = "/post",
      method = RequestMethod.GET)
  public String composer(ModelMap model) {
    try {
      connectionRepository.getPrimaryConnection(Facebook.class).getApi();
      model.addAttribute("authname", SecurityContextHolder.getContext().getAuthentication()
              .getName());
    } catch (NotConnectedException e) {
        model.addAttribute("authname", SecurityContextHolder.getContext().getAuthentication()
                .getName());
      return "facebook/connect";
    }
    return "post";
  }

  @RequestMapping(
      value = "/sharesupc",
      method = RequestMethod.GET)
  public String composesupc(ModelMap model) {
    try {
      connectionRepository.getPrimaryConnection(Facebook.class).getApi();
    } catch (NotConnectedException e) {
      return "facebook/connect";
    }
    return "postsupc";
  }

  @RequestMapping(
      value = "/friends",
      method = RequestMethod.GET)
  public String showFeed(Model model) {
    try {
      Facebook facebook = connectionRepository.getPrimaryConnection(Facebook.class).getApi();
      PagedList<FacebookProfile> friends = facebook.friendOperations().getFriendProfiles();
      model.addAttribute("friends", friends);
      SharedData data = new SharedData();
      List<String> test = new ArrayList<String>();
      for (FacebookProfile friend : friends) {
        test.add(friend.getName());
      }
      data.setFriendsIds(test);
      model.addAttribute("data", data);

      return "facebook/friends";
    } catch (NotConnectedException e) {
      return "facebook/connect";
    }

  }

  @RequestMapping(
      value = "/sharesupc",
      method = RequestMethod.POST)
  public String postsupc(@ModelAttribute("data") SharedData friends, BindingResult result,
                         Model model) {
    try {
      Facebook facebook = connectionRepository.getPrimaryConnection(Facebook.class).getApi();
      // facebook.feedOperations().post(ownerId, message);

      ObjectMapper mapper = new ObjectMapper();
      SharedSUPCObject p = new SharedSUPCObject();
      p.setDescription("Kohls SUPC Code");
      p.setTitle("Single Use Promotion Code");
      p.setSupccode(SUPCGenerator.encrypt(12232, SUPCGenerator.getNextRandom()));
      p.setUrl("http://dry-everglades-5630.herokuapp.com/");
      MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
      data.add("object", mapper.writeValueAsString(p));
      String responseId = facebook.publish("me/objects", "meandkohlsshare:coupon", data);
      System.out.println("ID:" + responseId);
      System.out.println(friends.getFriendsIds());
      String flist = "";
      int count = 0;
      for (String id : friends.getFriendsIds()) {
        System.out.println(id);
        // facebook.feedOperations().post(id, "Sameer from Kohls has shared a coupon with you");
        if (count >= 1) {
          flist += ",";
        }
        flist += id;
        count++;
        // facebook.restOperations().postForObject("https://graph.facebook.com/"+id+"/meandkohlsshare:sharesupc",
        // , arg2)
      }
      MultiValueMap<String, Object> data1 = new LinkedMultiValueMap<String, Object>();
      data1.add("tags", flist);
      data1.add("message", "Sameer Has Shared with you the SUPC Code:" + p.getSupccode());
      data1.add("coupon", responseId);
      String responseId1 = facebook.publish("me", "meandkohlsshare:supccouponcode", data1);
      System.out.println("ID:" + responseId1);

    } catch (NotConnectedException e) {
      return "facebook/connect";
    } catch (OperationNotPermittedException o) {
      return "facebook/connect";
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return "facebook/friends";
  }

  @RequestMapping(
      value = "/post",
      method = RequestMethod.POST)
  public String post(String message, ModelMap model) {
    try {
      Facebook facebook = connectionRepository.getPrimaryConnection(Facebook.class).getApi();
      facebook.feedOperations().updateStatus(message);
      model.addAttribute("status", "success");
      model.addAttribute("message", message);
      return "posted";
    } catch (Exception e) {
      model.addAttribute("status", "failure");
      return "posted";
    }
  }
}
