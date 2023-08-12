package com.springstudy.study.online_movie_theater.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldErrorDTO {
    private final String objectName;
    private final String field;
    private final String message;
}
