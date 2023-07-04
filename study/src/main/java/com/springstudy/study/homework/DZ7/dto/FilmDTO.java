package com.springstudy.study.homework.DZ7.dto;

import com.springstudy.study.homework.DZ7.model.Genre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class FilmDTO extends GenericDTO{
    private String filmTitle;
    private String duration;
    private LocalDate premierYear;
    private String country;
    private Genre genre;
    private String description;
    private String filmStudio;
    private  Integer amount;
    private List<Long> directorsIds;
    private List<DirectorDTO> directorInfo;
}
