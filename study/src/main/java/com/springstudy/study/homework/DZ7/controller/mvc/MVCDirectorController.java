package com.springstudy.study.homework.DZ7.controller.mvc;

import com.springstudy.study.homework.DZ7.exception.MyDeleteException;
import com.springstudy.study.homework.DZ7.dto.AddFilmDTO;
import com.springstudy.study.homework.DZ7.dto.DirectorDTO;
import com.springstudy.study.homework.DZ7.service.DirectorsService;
import com.springstudy.study.homework.DZ7.service.FilmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.springstudy.study.homework.DZ7.constants.UserRolesConstants.ADMIN;

@Slf4j
@Controller
@RequestMapping("/director")
public class MVCDirectorController {
    private final DirectorsService directorsService;
    private final FilmsService filmsService;
    public MVCDirectorController(DirectorsService directorsService, FilmsService filmsService) {this.directorsService = directorsService;
        this.filmsService = filmsService;
    }
    @GetMapping
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "5") int pageSize,
                         Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "directorFIO"));
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Page<DirectorDTO> result;
        if (ADMIN.equalsIgnoreCase(userName)) {
            result = directorsService.listAll(pageRequest);
        }
        else {
            result = directorsService.listAllNotDeleted(pageRequest);
        }
        model.addAttribute("directors", result);

        return "director/vievAllDirectors";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable Long id,
                         Model model) {
        model.addAttribute("directors", directorsService.getOne(id));
        return "director/viewDirector";
    }

    @GetMapping("/add")
    public String create() {
        return "director/addDirector";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("directorForm") DirectorDTO directorDTO) {
        log.info(directorDTO.toString());
        directorsService.create(directorDTO);
        return "redirect:/director";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         Model model) {
        model.addAttribute("directors", directorsService.getOne(id));
        return "director/updateDirector";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("directorForm") DirectorDTO directorDTO) {
        directorsService.update(directorDTO);
        return "redirect:/director";
    }

    @GetMapping("/add-film/{directorId}")
    public String addFilm(@PathVariable Long directorId,
                          Model model) {
        model.addAttribute("films", filmsService.listAll());
        model.addAttribute("directorId", directorId);
        model.addAttribute("directors", directorsService.getOne(directorId).getDirectorFIO());
        return "director/addDirectorFilm";
    }

    @PostMapping("/add-film")
    public String addFilm(@ModelAttribute("directorFilmForm")AddFilmDTO addFilmDTO) {
        directorsService.addFilm(addFilmDTO);
        return "redirect:/director";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) throws MyDeleteException {
        directorsService.deleteSoft(id);
        return "redirect:/director";
    }

    @GetMapping("/restore/{id}")
    public String restore(@PathVariable Long id) {
        directorsService.restore(id);
        return "redirect:/director";
    }

    @PostMapping("/search")
    public String searchDirectors(@RequestParam(value = "page", defaultValue = "1") int page,
                                @RequestParam(value = "size", defaultValue = "5") int pageSize,
                                @ModelAttribute("directorSearchForm") DirectorDTO directorDTO,
                                Model model) {
        if (StringUtils.hasText(directorDTO.getDirectorFIO()) || StringUtils.hasLength(directorDTO.getDirectorFIO())) {
            PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "directorFIO"));
            model.addAttribute("directors", directorsService.searchDirectors(directorDTO.getDirectorFIO().trim(), pageRequest));
            return "director/vievAllDirectors";
        }
        else {
            return "redirect:/director";
        }
    }

}
