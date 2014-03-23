package com.yatrix.activity.service.controllers;

import com.yatrix.activity.store.mongo.domain.Role;
import com.yatrix.activity.store.mongo.domain.UserAccount;
import com.yatrix.activity.store.mongo.repository.UserAccountRepository;
import com.yatrix.activity.store.utils.UserAdminService;
import com.yatrix.activity.service.dto.UserDto;
import com.yatrix.activity.service.utils.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserAccountRepository repository;

  @Autowired
  private UserAdminService service;

  @ModelAttribute("allRoles")
  public Role[] getAllRoles() {
    return new Role[] { Role.ROLE_USER };
  }

  @RequestMapping
  public String getUsersPage(ModelMap model) {
    Pageable pageRequest = new PageRequest(0, 100);
    Page<UserAccount> users = repository.findAll(pageRequest);
    model.addAttribute("users", UserMapper.map(users));
    model.addAttribute("commanduser", new UserDto());
    model.addAttribute("usertype", "new");
    return "users";
  }

  @RequestMapping(
      value = "/get",
      produces = "application/json")
  public @ResponseBody
  UserDto get(@RequestBody UserDto user) {
    return UserMapper.map(repository.findByUserId(user.getUsername()));
  }

  @RequestMapping(
      value = "/create",
      produces = "application/json",
      method = RequestMethod.POST)
  public String create(UserDto dto) {
    if (dto.getId() != null) {
      UserAccount existingUser = UserMapper.map(dto);
      existingUser.setRoles(new Role[] { Role.ROLE_USER });
      service.update(existingUser);
    } else {
      UserAccount newUser = UserMapper.map(dto);
      newUser.setRoles(new Role[] { Role.ROLE_USER });
      service.create(newUser);
    }
    return "redirect:/users";
  }

  @RequestMapping(
      value = "/edit")
  public String edit(String id, ModelMap model) {
    Pageable pageRequest = new PageRequest(0, 100);
    Page<UserAccount> users = repository.findAll(pageRequest);
    model.addAttribute("users", UserMapper.map(users));
    model.addAttribute("commanduser", UserMapper.map(repository.findOne(id)));
    model.addAttribute("usertype", "update");
    return "users";
  }

  @RequestMapping(
      value = "/delete")
  public String delete(String id) {
    UserAccount existingUser = new UserAccount();
    existingUser.setId(id);
    service.delete(existingUser);
    return "redirect:/users";
  }
}
