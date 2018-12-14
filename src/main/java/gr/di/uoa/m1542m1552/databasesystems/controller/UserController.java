package gr.di.uoa.m1542m1552.databasesystems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gr.di.uoa.m1542m1552.databasesystems.domain.User;
import gr.di.uoa.m1542m1552.databasesystems.service.UserService;
import gr.di.uoa.m1542m1552.databasesystems.security.AuthToken;
import gr.di.uoa.m1542m1552.databasesystems.security.JwtTokenUtil;
import gr.di.uoa.m1542m1552.databasesystems.security.UserLogin;
import gr.di.uoa.m1542m1552.databasesystems.security.WebSecurityConfig;

@RestController
class UserController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private WebSecurityConfig webSecurityConfig;

  @Autowired
  UserService userService;

  // User login
  @PostMapping("/users/login")
  public ResponseEntity<AuthToken> login(@RequestBody UserLogin loginUser) throws AuthenticationException {
  System.out.println("before");
      final Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      loginUser.getEmail(),
                      loginUser.getPassword()
              )
      );
      System.out.println("after");
      SecurityContextHolder.getContext().setAuthentication(authentication);
      final User user = userService.findByEmail(loginUser.getEmail());
      final String token = jwtTokenUtil.generateToken(user);

      return ResponseEntity.ok(new AuthToken(token, loginUser.getEmail()));
  }

  // User email existance check
  @GetMapping("/users/email_exists/{email:.+}")
  public boolean emailExists(@PathVariable("email") String email) {
    System.out.println(email);
    return userService.emailExists(email);
  }

  // User registration
  @PostMapping("/users")
  public User createUser(@RequestBody User newUser) {
    // TODO: Validation needed
    newUser.setPassword(webSecurityConfig.passwordEncoder().encode(newUser.getPassword()));
    return userService.createUser(newUser);
  }

}
