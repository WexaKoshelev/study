package com.springstudy.study.online_movie_theater.service;

import com.springstudy.study.online_movie_theater.constants.Errors;
import com.springstudy.study.online_movie_theater.dto.AddFilmDTO;
import com.springstudy.study.online_movie_theater.dto.DirectorDTO;
import com.springstudy.study.online_movie_theater.exception.MyDeleteException;
import com.springstudy.study.online_movie_theater.mapper.DirectorsMapper;
import com.springstudy.study.online_movie_theater.model.Directors;
import com.springstudy.study.online_movie_theater.model.Films;
import com.springstudy.study.online_movie_theater.repository.DirectorsRepository;
import com.springstudy.study.online_movie_theater.repository.FilmsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class DirectorsService extends GenericService<Directors, DirectorDTO> {
    private final FilmsRepository filmsRepository;

    public DirectorsService(DirectorsRepository directorsRepository, DirectorsMapper directorsMapper,
                            FilmsRepository filmsRepository) {
        super(directorsRepository, directorsMapper);
        this.filmsRepository = filmsRepository;
    }
    public DirectorDTO addFilm (AddFilmDTO addFilmDTO){
        DirectorDTO director = getOne(addFilmDTO.getDirectorId());
        director.getFilmsId().add(addFilmDTO.getFilmId());
        update(director);
        return director;
    }
    public Page<DirectorDTO> searchDirectors(final String fio,
                                             Pageable pageable) {
        Page<Directors> directors = ((DirectorsRepository)repository).findAllByDirectorFIOContainsIgnoreCaseAndIsDeletedFalse(fio, pageable);
        List<DirectorDTO> result = mapper.toDTOs(directors.getContent());
        return new PageImpl<>(result, pageable, directors.getTotalElements());
    }

    @Override
    public void deleteSoft(Long objectId) throws MyDeleteException {
        Directors directors = repository.findById(objectId).orElseThrow(
                () -> new NotFoundException("Автора с заданным id=" + objectId + " не существует."));
        boolean authorCanBeDeleted = ((DirectorsRepository)repository).checkDirectorForDeletion(objectId);
        if (authorCanBeDeleted) {
            markAsDeleted(directors);
            List<Films> films = directors.getFilms();
            if (films != null && films.size() > 0) {
                films.forEach(this::markAsDeleted);
            }
            ((DirectorsRepository)repository).save(directors);
        }
        else {
            throw new MyDeleteException(Errors.Directors.DIRECTOR_DELETE_ERROR);
        }
    }

    public void restore(Long objectId) {
        Directors directors = repository.findById(objectId).orElseThrow(
                () -> new NotFoundException("Автора с заданным id=" + objectId + " не существует."));
        unMarkAsDeleted(directors);
        List<Films> films = directors.getFilms();
        if (films != null && films.size() > 0) {
            films.forEach(this::unMarkAsDeleted);
        }
        repository.save(directors);
    }

}
