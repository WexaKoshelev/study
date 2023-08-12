package com.springstudy.study.online_movie_theater.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddFilmDTO {
    Long filmId;
    Long directorId;
}
