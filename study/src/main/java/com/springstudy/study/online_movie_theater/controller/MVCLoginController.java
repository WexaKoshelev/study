package com.springstudy.study.online_movie_theater.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class MVCLoginController {

    @GetMapping
    public String login() {return "login";}
}

