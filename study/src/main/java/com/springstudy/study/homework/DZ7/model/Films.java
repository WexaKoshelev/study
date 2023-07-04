package com.springstudy.study.homework.DZ7.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
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
    private LocalDate premierYear;

    @Column(name = "country", nullable = false)
    private String country;

    @Enumerated
    @Column(name = "genre", nullable = false)
    private Genre genre;



    @ManyToMany
    @JoinTable(name = "FilmDirectors",
            joinColumns = @JoinColumn(name = "director_id"), foreignKey = @ForeignKey(name = "FK_DIRECTORS_FILMS"),
            inverseJoinColumns = @JoinColumn(name = "film_id"), inverseForeignKey = @ForeignKey(name = "FK_FILMS_DIRECTORS"))
    List <Directors> directors;

    @OneToMany(mappedBy = "films")
    private List<Orders> orders;

}
