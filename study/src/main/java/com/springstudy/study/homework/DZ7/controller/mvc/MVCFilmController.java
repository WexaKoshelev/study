package com.springstudy.study.homework.DZ7.controller.mvc;

import com.springstudy.study.homework.DZ7.dto.FilmDTO;
import com.springstudy.study.homework.DZ7.service.FilmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/films")
public class MVCFilmController {
    private final FilmsService filmsService;
    public MVCFilmController(FilmsService filmsService) {
        this.filmsService = filmsService;
    }
    @GetMapping
    public  String getAll (Model model) {
        List<FilmDTO> films = filmsService.listAll();
        model.addAttribute("films", films);
        return "films/vievAllFilms";
    }
    @GetMapping("/add")
    public String create () {
        return "films/addFilm";
    }
    @PostMapping("/add")
    public String create (@ModelAttribute("filmFrom") FilmDTO newFilm) {
        log.info(newFilm.toString());
        filmsService.create(newFilm);
        return "redirect:/films";
    }

}
