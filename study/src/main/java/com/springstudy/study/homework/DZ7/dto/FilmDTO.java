package com.springstudy.study.homework.DZ7.dto;

import com.springstudy.study.homework.DZ7.model.Directors;
import com.springstudy.study.homework.DZ7.model.Genre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class FilmDTO extends GenericDTO{
    private String filmTitle;
    private Integer premierYear;
    private String country;
    private Genre genre;
    private List<Long> directorsIds;
}
