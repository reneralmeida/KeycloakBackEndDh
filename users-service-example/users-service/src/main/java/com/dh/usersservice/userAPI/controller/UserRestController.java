package com.dh.usersservice.userAPI.controller;

import com.dh.usersservice.model.User;

import com.dh.usersservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class UserRestController {
  @Autowired
  private UserService userService;
  @GetMapping("/users")
  public User getAll(@RequestParam  String id, @RequestParam String color){
    return userService.updateColor(id,color);
  }
  @GetMapping("/users/idade")
  public User getIdade(@RequestParam  String id, @RequestParam String idade){
    return userService.updateIdade(id,idade);
  }
}
