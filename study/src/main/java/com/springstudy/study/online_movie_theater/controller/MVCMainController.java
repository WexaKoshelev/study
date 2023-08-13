package com.springstudy.study.online_movie_theater.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MVCMainController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}

