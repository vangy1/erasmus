package com.erasmus.controller;


import com.erasmus.authentication.AuthenticationService;
import com.erasmus.dto.RaspberryDto;
import com.erasmus.service.RaspberryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/raspberry")
public class RaspberryController {

    AuthenticationService authenticationService;
    RaspberryService raspberryService;

    @Autowired
    public RaspberryController(AuthenticationService authenticationService, RaspberryService raspberryService) {
        this.authenticationService = authenticationService;
        this.raspberryService = raspberryService;
    }

    @GetMapping("/voting")
    private RaspberryDto getCurrentVoting() {
        return raspberryService.getCurrentVoting();
    }

    @PostMapping("/vote")
    private RaspberryDto vote(@RequestHeader("Authorization") String authorization, @RequestParam("rating") String rating) {
        return raspberryService.vote(authorization, rating);
    }
}
