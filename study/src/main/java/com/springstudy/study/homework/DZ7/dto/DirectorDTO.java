package com.springstudy.study.homework.DZ7.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@ToString
@NoArgsConstructor
@Getter
@Setter
public class DirectorDTO extends GenericDTO {
    private String filmTitle;
    private Integer position;
    List<Long> filmsId;

}
