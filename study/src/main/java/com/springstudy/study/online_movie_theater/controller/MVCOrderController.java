package com.springstudy.study.online_movie_theater.controller;

import com.springstudy.study.online_movie_theater.service.FilmsService;
import com.springstudy.study.online_movie_theater.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@Slf4j
@RequestMapping("/rent")
public class MVCOrderController {
    private final FilmsService filmsService;
    private final OrdersService ordersService;

    public MVCOrderController(FilmsService filmsService, OrdersService ordersService) {
        this.filmsService = filmsService;
        this.ordersService = ordersService;
    }
    @GetMapping("/film/{filmId}")
    public String rentBook(@PathVariable Long filmId,
                           Model model) {
        model.addAttribute("book", filmsService.getOne(filmId));
        return "userFilms/rentFilm";
    }

}