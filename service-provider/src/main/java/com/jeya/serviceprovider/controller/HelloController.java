package com.jeya.serviceprovider.controller;

import javax.servlet.http.HttpServletRequest;

import com.jeya.serviceprovider.service.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController
{
  @Autowired
  private TokenValidator tokenValidator;

  @GetMapping("/hello")
  public ResponseEntity<?> index(HttpServletRequest request)
  {
    //String output = ("<html><head><title>Service Provider</title></head>" + "<body><h1>Error</h1><p>Invalid token</p></body></html>");
    String output = "Invalid token";
    if (tokenValidator.isValid(request))
    {
      String greeting = ("Hello " + tokenValidator.getUser());
      String account = ("Account # " + tokenValidator.getAccount());
      output = output.replaceAll("Error", "Welcome").replaceAll("Invalid token", greeting + "</p><p>" + account);
    }
    return (new ResponseEntity<>(output, HttpStatus.OK));
  }
}
