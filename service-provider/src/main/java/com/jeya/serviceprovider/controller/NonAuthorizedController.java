package com.jeya.serviceprovider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NonAuthorizedController
{
  @RequestMapping({ "/hello" })
  public String helloPage()
  {
    return "Hello world!";
  }
}
