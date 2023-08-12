package com.springstudy.study.online_movie_theater.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Directors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "directors_sequence", allocationSize = 1)
public class Directors extends  GenericModel{

    @Column(name = "directors_fio", nullable = false)
    private String directorFIO;

    @Column(name = "position", nullable = false)
    private Integer position;

    @ManyToMany(mappedBy = "directors")
    List <Films> films;

}
