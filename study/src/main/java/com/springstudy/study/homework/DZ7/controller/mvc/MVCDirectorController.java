package com.springstudy.study.homework.DZ7.controller.mvc;

import com.springstudy.study.homework.DZ7.dto.DirectorDTO;
import com.springstudy.study.homework.DZ7.service.DirectorsService;
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
@RequestMapping("/directors")
public class MVCDirectorController {
    private final DirectorsService directorsService;
    public MVCDirectorController(DirectorsService directorsService) {this.directorsService = directorsService;}
    @GetMapping
    public String getAll (Model model) {
        List<DirectorDTO> directors = directorsService.listAll();
        model.addAttribute("directors", directors);
        return "director/vievAllDirectors";
    }
    @GetMapping("/add")
    public String create () {
        return "director/addDirector";
    }
    @PostMapping("/add")
    public String create (@ModelAttribute("directorFrom") DirectorDTO newDirector) {
        log.info(newDirector.toString());
        directorsService.create(newDirector);
        return "redirect:/directors";
    }
}
