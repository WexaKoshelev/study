package com.springstudy.study.homework.DZ7.controller.mvc;

import com.springstudy.study.homework.DZ7.dto.FilmDTO;
import com.springstudy.study.homework.DZ7.service.FilmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "5") int pageSize,
                         Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "filmTitle"));
        Page<FilmDTO> films = filmsService.getAllFilms(pageRequest);
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
