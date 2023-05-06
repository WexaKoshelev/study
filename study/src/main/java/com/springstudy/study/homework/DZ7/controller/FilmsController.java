package com.springstudy.study.homework.DZ7.controller;

import com.springstudy.study.homework.DZ7.model.Films;
import com.springstudy.study.homework.DZ7.repository.DirectorsRepository;
import com.springstudy.study.homework.DZ7.repository.FilmsRepository;
import com.springstudy.study.homework.DZ7.repository.GenericRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
