package com.springstudy.study.homework.DZ7.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Films")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "films_sequence", allocationSize = 1)
public class Films extends GenericModel {
    @Column(name = "title", nullable = false)
    private String filmTitle;

    @Column(name = "premier_year", nullable = false)
    private Integer premierYear;

    @Column(name = "country", nullable = false)
    private String country;

    @Enumerated
    @Column(name = "genre", nullable = false)
    private Genre genre;

    @ManyToMany(mappedBy = "films")
    List <Directors> directors;

    @OneToMany(mappedBy = "films")
    private List<Orders> orders;

}
