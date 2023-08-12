package com.coursework.coursework.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "worker")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Worker extends GenericModel{
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "worked_out", nullable = false)
    private Integer workedOut;

    @Column(name = "bet", nullable = false)
    private Integer bet;

}
