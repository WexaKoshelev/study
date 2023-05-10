package com.springstudy.study.homework.DZ7.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public abstract class GenericDTO {
    private Long id;
    private String createdBy;
    private LocalDateTime deletedWhen;
    private String deletedBy;
    private boolean isDeleted;
}
