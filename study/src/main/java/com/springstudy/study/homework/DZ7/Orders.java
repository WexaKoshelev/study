package com.springstudy.study.homework.DZ7;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "orders_sequence", allocationSize = 1)
public class Orders extends GenericModel{
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_ORDER_USER"))
    private Users users;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false, foreignKey = @ForeignKey(name = "FK_ORDER_Film"))
    private Films films;

    @Column(name = "rentDate", nullable = false)
    private Integer rentDate;

    @Column(name = "rentPeriod", nullable = false)
    private Integer rentPeriod;

    @Column(name = "purchase", nullable = false)
    private Integer purchase;
}
