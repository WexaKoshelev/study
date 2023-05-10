package com.springstudy.study.homework.DZ7.service;

import com.springstudy.study.homework.DZ7.dto.FilmDTO;
import com.springstudy.study.homework.DZ7.mapper.FilmsMapper;
import com.springstudy.study.homework.DZ7.model.Directors;
import com.springstudy.study.homework.DZ7.model.Films;
import com.springstudy.study.homework.DZ7.repository.DirectorsRepository;
import com.springstudy.study.homework.DZ7.repository.FilmsRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
@Service
public class FilmsService extends GenericService<Films, FilmDTO> {
    private final DirectorsRepository directorsRepository;

    protected FilmsService(FilmsRepository repository, FilmsMapper mapper, DirectorsRepository directorsRepository) {
        super(repository, mapper);
        this.directorsRepository = directorsRepository;
    }
    public FilmDTO addDirector (final Long filmId, final Long directorId){
        FilmDTO film = getOne(filmId);
        Directors directors = directorsRepository.findById(directorId).orElseThrow(() -> new NotFoundException("Директор не найден"));
        update(film);
        return film;
    }
}
