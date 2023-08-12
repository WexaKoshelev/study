package com.springstudy.study.online_movie_theater.dto;

import com.springstudy.study.online_movie_theater.model.Directors;
import com.springstudy.study.online_movie_theater.model.Films;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DirectorDTO extends GenericDTO {
    private String directorFIO;
    private LocalDate birthDate;
    private Integer position;
    private String description;
    List<Long> filmsId;

    public DirectorDTO (Directors directors) {
        this.directorFIO = directors.getDirectorFIO();
        this.position = directors.getPosition();
        this.createdBy = directors.getCreatedBy();
        this.id = directors.getId();
        List<Films> films = directors.getFilms();
        List<Long> filmIds = new ArrayList<>();
        films.forEach(f -> filmIds.add(f.getId()));
        this.filmsId = filmIds;
        this.isDeleted = false;
    }


}
