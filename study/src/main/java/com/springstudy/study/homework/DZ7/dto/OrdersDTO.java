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
    private List<Long> userId;
    private List<Long> filmId;
    private LocalDateTime rentData;
    private LocalDateTime rentPeriod;
    private String purchase;


}
