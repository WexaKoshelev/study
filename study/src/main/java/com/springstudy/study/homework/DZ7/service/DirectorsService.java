package com.springstudy.study.homework.DZ7.service;

import com.springstudy.study.homework.DZ7.dto.DirectorDTO;
import com.springstudy.study.homework.DZ7.mapper.DirectorsMapper;
import com.springstudy.study.homework.DZ7.model.Directors;
import com.springstudy.study.homework.DZ7.model.Films;
import com.springstudy.study.homework.DZ7.repository.DirectorsRepository;
import com.springstudy.study.homework.DZ7.repository.FilmsRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
@Service
public class DirectorsService extends GenericService<Directors, DirectorDTO> {
    private final FilmsRepository filmsRepository;

    public DirectorsService(DirectorsRepository directorsRepository, DirectorsMapper directorsMapper,
                            FilmsRepository filmsRepository) {
        super(directorsRepository, directorsMapper);
        this.filmsRepository = filmsRepository;
    }
    public DirectorDTO addFilm (Long filmId, Long directorId){
        Films films = filmsRepository.findById(filmId).orElseThrow(() -> new NotFoundException("Фильм не найден"));
        DirectorDTO director = getOne(directorId);
        director.getFilmsId().add(films.getId());
        update(director);
        return director;
    }
}
