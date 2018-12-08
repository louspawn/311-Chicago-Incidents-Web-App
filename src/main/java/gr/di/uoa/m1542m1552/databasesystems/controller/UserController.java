package gr.di.uoa.m1542m1552.databasesystems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gr.di.uoa.m1542m1552.databasesystems.domain.User;
import gr.di.uoa.m1542m1552.databasesystems.service.UserService;

@RestController
class UserController {

  @Autowired
  UserService userService;

  //Requests
  @PostMapping("/users")
  public User createUser(@RequestBody User newUser) {
    return userService.createUser(newUser);
  }

}
