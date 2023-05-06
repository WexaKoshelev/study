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
@RequestMapping("/films")
@Tag(name = "Фильмы", description = "Контроллер для работы с фильмами")
public class FilmsController extends GenericController<Films>{
    private final FilmsRepository filmsRepository;
    private final DirectorsRepository directorsRepository;

    public FilmsController(GenericRepository<Films> genericRepository, FilmsRepository filmsRepository, DirectorsRepository directorsRepository) {
        super(genericRepository);
        this.filmsRepository = filmsRepository;
        this.directorsRepository = directorsRepository;
    }
    @Operation(description = "Добавить фильм к директору")
    @RequestMapping(value = "/addDirector", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Films> addDirector(@RequestParam(value = "director_id") Long director_id,
                                              @RequestParam(value = "film_id") Long film_id) {
        Directors directors = directorsRepository.findById(director_id).orElseThrow(() -> new NotFoundException("Директор не найден"));
        Films films = filmsRepository.findById(film_id).orElseThrow(() -> new NotFoundException("Фильм не найде"));
        films.getDirectors().add(directors);
        return ResponseEntity.status(HttpStatus.OK).body(filmsRepository.save(films));
    }

}
