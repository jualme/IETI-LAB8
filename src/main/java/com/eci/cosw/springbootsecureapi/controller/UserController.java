package com.eci.cosw.springbootsecureapi.controller;

import com.eci.cosw.springbootsecureapi.model.User;
import com.eci.cosw.springbootsecureapi.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Date;

/** @author Santiago Carrillo 8/21/17. */
@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

  @Autowired private UserService userService;

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public Token login(@RequestBody User login) throws ServletException {

    String jwtToken = "";

    if (login.getUsername() == null || login.getPassword() == null) {
      throw new ServletException("Please fill in username and password");
    }
    User user = userService.findUserByEmailAndPassword(login.getUsername(),login.getPassword());
    if (user == null){
      throw new ServletException("Invalid login. Please check your name and password.");
    }
    jwtToken =
        Jwts.builder()
            .setSubject(login.getUsername())
            .claim("roles", "user")
            .setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256, "secretkey")
            .compact();

    return new Token(jwtToken);
  }

  public class Token {

    String accessToken;

    public Token(String accessToken) {
      this.accessToken = accessToken;
    }

    public String getAccessToken() {
      return accessToken;
    }

    public void setAccessToken(String access_token) {
      this.accessToken = access_token;
    }
  }
}
