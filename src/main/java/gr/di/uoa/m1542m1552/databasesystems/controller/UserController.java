package gr.di.uoa.m1542m1552.databasesystems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gr.di.uoa.m1542m1552.databasesystems.domain.User;
import gr.di.uoa.m1542m1552.databasesystems.service.UserService;

@RestController
class UserController {

  @Autowired
  UserService userService;

  // User email existance check
  @PostMapping("/email_exists")
  public boolean emailExists(@RequestBody String email) {
    return userService.emailExists(email);
  }

  // User registration
  @PostMapping("/users")
  public User createUser(@RequestBody User newUser) {
    // TODO: Validation needed
    newUser.setPassword(passwordEncoder().encode(newUser.getPassword()));
    return userService.createUser(newUser);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }

}
