package com.hbs.auracar.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "car", indexes = {
        @Index(name = "car_carregistration_index", columnList = "carregistration"),
        @Index(name = "car_model_index", columnList = "model")
})
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "model", nullable = false)
    private String model;

    @Lob
    @Column(name = "carregistration", nullable = false)
    private String carRegistration;

    @Column(name = "entry", nullable = false)
    private Instant entry;

    @Column(name = "exit")
    private Instant exit;

}
