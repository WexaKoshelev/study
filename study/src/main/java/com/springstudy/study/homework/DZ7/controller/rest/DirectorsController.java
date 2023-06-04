package com.springstudy.study.homework.DZ7.controller.rest;

import com.springstudy.study.homework.DZ7.dto.DirectorDTO;
import com.springstudy.study.homework.DZ7.model.Directors;
import com.springstudy.study.homework.DZ7.service.DirectorsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/directors")
@Tag(name = "Директор", description = "Контроллер для работы с директороми фильмов")
public class DirectorsController extends GenericController <Directors, DirectorDTO> {
    public DirectorsController(DirectorsService directorsService) {super(directorsService);}



    @Operation(description = "Добавить директора к фильмам")
    @RequestMapping(value = "/addFilms", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DirectorDTO> addDirector(@RequestParam(value = "director_id") Long directorid,
                                                 @RequestParam(value = "film_id") Long filmid) {
        return ResponseEntity.status(HttpStatus.OK).body(((DirectorsService)service).addFilm(directorid,filmid));
    }
}
