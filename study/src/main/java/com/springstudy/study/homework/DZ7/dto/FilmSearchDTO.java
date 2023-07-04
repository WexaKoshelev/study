package com.springstudy.study.homework.DZ7.dto;

import com.springstudy.study.homework.DZ7.model.Genre;
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

