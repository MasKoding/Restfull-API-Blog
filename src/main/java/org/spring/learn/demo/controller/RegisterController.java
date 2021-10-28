package org.spring.learn.demo.controller;

import org.spring.learn.demo.request.RegisterUserRequest;
import org.spring.learn.demo.service.RegisterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/registration")
@AllArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @RequestMapping(value="",method=RequestMethod.POST)
    public String userRegistration(@RequestBody RegisterUserRequest registerUserRequest){
        return registerService.register(registerUserRequest);
    }
    
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){
        return registerService.confirmToken(token);
    }

}
