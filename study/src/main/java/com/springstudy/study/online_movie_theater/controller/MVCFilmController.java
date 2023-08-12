package com.springstudy.study.online_movie_theater.controller;

import com.springstudy.study.online_movie_theater.dto.FilmDTO;
import com.springstudy.study.online_movie_theater.exception.MyDeleteException;
import com.springstudy.study.online_movie_theater.service.FilmsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.webjars.NotFoundException;

import java.nio.file.AccessDeniedException;

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
    @GetMapping("/{id}")
    public String getOne (@PathVariable Long id, Model model) {
        model.addAttribute("film", filmsService.getOne(id));
        return "films/viewFilm";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("film", filmsService.getOne(id));
        return "films/updateFilm";
    }
    @PostMapping
    public String update(@ModelAttribute("filmForm") FilmDTO filmDTO) {
        filmsService.update(filmDTO);
        return "redirect:/films";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) throws MyDeleteException {
        filmsService.deleteSoft(id);
        return "redirect:/films";
    }
    @GetMapping("/restore/{id}")
    public String restore(@PathVariable Long id) {
        filmsService.restore(id);
        if (id.equals(123L)) {
            throw new NotFoundException("НЕ НАЙДЕНО!");
        }
        return "redirect:/films";
    }


//    @PostMapping("/search")
//    public String searchFilms(@RequestParam(value = "page", defaultValue = "1") int page,
//                              @RequestParam(value = "size", defaultValue = "5") int pageSize,
//                              @ModelAttribute("filmSearchForm")FilmSearchDTO filmSearchDTO,
//                              Model model) {
//        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "title"));
//        model.addAttribute("films", filmsService.searchFilm(filmSearchDTO, pageRequest));
//        return "films/viewAllFilms";
//    }


    /**
     * Метод для поиска книги по ФИО автора (редирект по кнопке "Посмотреть книги" на странице автора)
     *
     * @param page      - текущая страница
     * @param pageSize  - количество объектов на странице
     * @param authorDTO - ДТО автора
     * @param model     - модель
     * @return - форму со списком всех книг подходящих под критерии (по фио автора)
     */
//    @PostMapping("/search/filmsByDirector")
//    public String searchBooks(@RequestParam(value = "page", defaultValue = "1") int page,
//                              @RequestParam(value = "size", defaultValue = "5") int pageSize,
//                              @ModelAttribute("directorSearchForm")DirectorDTO directorDTO,
//                              Model model) {
//        FilmSearchDTO filmSearchDTO = new FilmSearchDTO();
//        filmSearchDTO.setDirectorFIO(directorDTO.getDirectorFIO());
//        return searchFilms(page, pageSize, filmSearchDTO, model);
//    }

//    @GetMapping(value = "/download", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @ResponseBody
//    public ResponseEntity<Resource> downloadBook(@Param(value = "filmId") Long filmId) throws IOException {
//        FilmDTO filmDTO = filmsService.getOne(filmId);
//        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
//
//        return ResponseEntity.ok()
//                .headers(createHeaders(path.getFileName().toString()))
//                .contentLength(path.toFile().length())
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .body(resource);
//    }

    private HttpHeaders createHeaders(final String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name);
        headers.add("Cache-Control", "no-cache, no-store");
        headers.add("Expires", "0");
        return headers;
    }

    @ExceptionHandler({MyDeleteException.class, AccessDeniedException.class, NotFoundException.class})
    public RedirectView handleError(HttpServletRequest request,
                                    Exception exception,
                                    RedirectAttributes redirectAttributes) {
        log.error("Запрос " + request.getRequestURL() + " вызвал ошибку: " + exception.getMessage());
        redirectAttributes.addFlashAttribute("exception", exception.getMessage());
        return new RedirectView("/films", true);
    }
}
