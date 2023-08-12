package com.springstudy.study.online_movie_theater.dto;

import com.springstudy.study.online_movie_theater.model.Genre;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FilmSearchDTO {
    private String filmTitle;
    private String directorFIO;
    private Genre genre;
}

