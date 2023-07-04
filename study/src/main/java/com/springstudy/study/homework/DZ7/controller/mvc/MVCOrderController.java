package com.springstudy.study.homework.DZ7.controller.mvc;

import com.springstudy.study.homework.DZ7.dto.OrdersDTO;
import com.springstudy.study.homework.DZ7.service.FilmsService;
import com.springstudy.study.homework.DZ7.service.OrdersService;
import com.springstudy.study.homework.DZ7.service.userdetails.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
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