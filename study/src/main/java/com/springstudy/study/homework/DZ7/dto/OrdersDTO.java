package com.springstudy.study.homework.DZ7.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class OrdersDTO extends  GenericDTO{
    private Long userId;
    private Long filmId;
    private LocalDateTime rentData;
    private Integer rentPeriod;
    private Boolean purchase;
    private FilmDTO filmDTO;

}
