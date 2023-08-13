package com.springstudy.study.online_movie_theater.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "orders_sequence", allocationSize = 1)
public class Orders extends GenericModel{
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_ORDER_USER"))
    private Users users;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "film_id", nullable = false, foreignKey = @ForeignKey(name = "FK_ORDER_Film"))
    private Films films;

    @Column(name = "rentDate", nullable = false)
    private LocalDateTime rentDate;

    @Column(name = "rentPeriod", nullable = false)
    private Integer rentPeriod;

    @Column(name = "purchase")
    private boolean purchase = false;
}
