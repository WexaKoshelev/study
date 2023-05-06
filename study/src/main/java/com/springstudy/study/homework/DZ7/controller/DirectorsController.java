package com.springstudy.study.homework.DZ7.controller;

import com.springstudy.study.homework.DZ7.model.Directors;
import com.springstudy.study.homework.DZ7.model.Films;
import com.springstudy.study.homework.DZ7.repository.DirectorsRepository;
import com.springstudy.study.homework.DZ7.repository.FilmsRepository;
import com.springstudy.study.homework.DZ7.repository.GenericRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

@RestController
@RequestMapping("/directors")
@Tag(name = "Директор", description = "Контроллер для работы с директороми фильмов")
public class DirectorsController extends GenericController <Directors> {
    private final DirectorsRepository directorsRepository;
    private final FilmsRepository filmsRepository;

    public DirectorsController(GenericRepository<Directors> genericRepository,
                               DirectorsRepository directorsRepository,
                               FilmsRepository filmsRepository) {
        super(genericRepository);
        this.directorsRepository = directorsRepository;
        this.filmsRepository = filmsRepository;
    }
    @Operation(description = "Добавить директора к фильмам")
    @RequestMapping(value = "/addFilms", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Directors> addDirector(@RequestParam(value = "director_id") Long director_id,
                                                 @RequestParam(value = "film_id") Long film_id) {
        Directors directors = directorsRepository.findById(director_id).orElseThrow(() -> new NotFoundException("Директор не найден"));
        Films films = filmsRepository.findById(film_id).orElseThrow(() -> new NotFoundException("Фильм не найде"));
        directors.getFilms().add(films);
        return ResponseEntity.status(HttpStatus.OK).body(directorsRepository.save(directors));
    }
}
