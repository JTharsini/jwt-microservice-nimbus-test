package com.jeya.tokenissuer.controller;

import com.jeya.tokenissuer.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController
{
  @Autowired
  private TokenService tokenService;

  @PostMapping({ "api/jwe" })
  public ResponseEntity<?> getToken()
  {
    String jwe = tokenService.getToken();
    return new ResponseEntity<>(jwe, HttpStatus.OK);
  }
}
