package com.springstudy.study.homework.DZ7.controller;

import com.springstudy.study.homework.DZ7.model.Directors;
import com.springstudy.study.homework.DZ7.repository.DirectorsRepository;
import com.springstudy.study.homework.DZ7.repository.FilmsRepository;
import com.springstudy.study.homework.DZ7.repository.GenericRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
