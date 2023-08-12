package com.springstudy.study.online_movie_theater.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public abstract class GenericDTO {
    protected Long id;
    protected String createdBy;
    protected LocalDateTime deletedWhen;
    protected String deletedBy;
    protected boolean isDeleted;
}
