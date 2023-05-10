package com.springstudy.study.homework.DZ7.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class OrdersDTO extends  GenericDTO{
    private Long usersId;
    private Long filmsId;
    private LocalDateTime rentData;
    private LocalDateTime rentPeriod;
    private boolean purchase;
}
