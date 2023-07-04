package com.springstudy.study.homework.DZ7.dto;

import com.springstudy.study.homework.DZ7.model.Directors;
import com.springstudy.study.homework.DZ7.model.Films;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DirectorDTO extends GenericDTO {
    private String directorFIO;
    private Integer birthDate;
    private String description;
    private Integer position;
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
