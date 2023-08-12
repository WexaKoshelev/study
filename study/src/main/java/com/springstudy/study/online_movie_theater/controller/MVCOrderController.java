package com.springstudy.study.online_movie_theater.controller;

import com.springstudy.study.online_movie_theater.dto.OrdersDTO;
import com.springstudy.study.online_movie_theater.service.FilmsService;
import com.springstudy.study.online_movie_theater.service.OrdersService;
import com.springstudy.study.online_movie_theater.service.userdetails.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public String rentFilm(@PathVariable Long filmId,
                           Model model) {
        model.addAttribute("film", filmsService.getOne(filmId));
        return "userFilms/rentFilm";
    }

    @PostMapping("/film")
    public String rentFilm(@ModelAttribute("rentFilmInfo")OrdersDTO ordersDTO) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ordersDTO.setUserId(Long.valueOf(customUserDetails.getUserId()));
        ordersService.rentFilm(ordersDTO);
        return "redirect:/rent/user-films/" + customUserDetails.getUserId();
    }

    @GetMapping("/return-film/{id}")
    public String returnFilm(@PathVariable Long id) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ordersService.returnFilm(id);
        return "redirect:/rent/user-films/" + customUserDetails.getUserId();
    }

    @GetMapping("/user-films/{id}")
    public String userBooks(@RequestParam(value = "page", defaultValue = "1") int page,
                            @RequestParam(value = "size", defaultValue = "5") int pageSize,
                            @PathVariable Long id,
                            Model model) {
//        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
//        Page<OrdersDTO> rentInfoDTOPage = ordersService.listUserRentFilms(id, pageRequest);
//        model.addAttribute("rentBooks", rentInfoDTOPage);
//        model.addAttribute("userId", id);
        return "userFilms/viewAllUserFilms";
    }


}