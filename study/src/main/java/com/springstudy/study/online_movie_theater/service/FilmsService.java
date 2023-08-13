package com.springstudy.study.online_movie_theater.service;

import com.springstudy.study.online_movie_theater.dto.FilmDTO;
import com.springstudy.study.online_movie_theater.mapper.FilmsMapper;
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
public class FilmsService extends GenericService<Films, FilmDTO> {
    private final DirectorsRepository directorsRepository;

    protected FilmsService(FilmsRepository repository, FilmsMapper mapper, DirectorsRepository directorsRepository) {
        super(repository, mapper);
        this.directorsRepository = directorsRepository;
    }

    public Page<FilmDTO> getAllFilms(Pageable pageable) {
        Page<Films> filmsPage = repository.findAll(pageable);
        List<FilmDTO> result = mapper.toDTOs(filmsPage.getContent());
        return new PageImpl<>(result, pageable, filmsPage.getTotalElements());
    }

    public FilmDTO addDirector(Long filmId, Long directorId) {
        FilmDTO film = getOne(filmId);
        Directors directors = directorsRepository.findById(directorId).orElseThrow(() -> new NotFoundException("Директор не найден"));
        update(film);
        return film;
    }
//    public Page<FilmDTO> searchFilm(FilmSearchDTO filmSearchDTO,
//                                    Pageable pageRequest) {
//
//        String genre = filmSearchDTO.getGenre() != null
//                ? String.valueOf(filmSearchDTO.getGenre().ordinal())
//                : null;
//
//        Page<Films> filmsPaginated = ((FilmsRepository) repository).searchFilms(
//                filmSearchDTO.getFilmTitle(),
//                genre,
//                filmSearchDTO.getDirectorFIO(),
//                pageRequest
//        );
//
//        List<FilmDTO> result = mapper.toDTOs(filmsPaginated.getContent());
//        return new PageImpl<>(result, pageRequest, filmsPaginated.getTotalElements());
//
//    }

//    @Override
//    public void deleteSoft(final Long id) throws MyDeleteException {
//       Films films= repository.findById(id).orElseThrow(() -> new NotFoundException("Фильм не найдено"));
//        boolean filmCanBeDeleted = ((FilmsRepository)repository).isFilmCanBeDeleted(id);
//        if (filmCanBeDeleted) {
//            markAsDeleted(films);
//            repository.save(films);
//        }
//        else {
//            throw new MyDeleteException(Errors.Films.FILM_DELETE_ERROR);
//        }
//    }

}
