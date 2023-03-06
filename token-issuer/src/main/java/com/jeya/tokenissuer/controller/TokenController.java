package com.jeya.tokenissuer.controller;

import java.util.stream.Collectors;
import javax.validation.Valid;

import com.jeya.tokenissuer.model.RequestData;
import com.jeya.tokenissuer.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController
{
  @Autowired
  private TokenService tokenService;
  private final Logger LOG = LoggerFactory.getLogger(TokenController.class);

  @PostMapping({ "api/jwe" })
  public ResponseEntity<?> getToken(@Valid @RequestBody final RequestData requestData, final Errors errors)
  {
    if (errors.hasErrors())
    {
      String errorMessage = errors.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.joining(","));
      LOG.error("Error = " + errorMessage);
      return ResponseEntity.badRequest().body(errorMessage);
    }
    String subject = requestData.getSubject();
    String jwe = tokenService.getToken(requestData);
    String json = ("{\"subject\":\"" + subject
                   + "\",\"token\":\"" + jwe + "\"}");
    LOG.info("Token generated for " + subject);
    final HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Bearer " + jwe);
    LOG.info("Authorization Header set with token");
    return (new ResponseEntity<>(json, headers, HttpStatus.OK));
  }
}
