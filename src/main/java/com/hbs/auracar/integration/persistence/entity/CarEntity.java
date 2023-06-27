package com.hbs.auracar.integration.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Getter
@Setter
@Table( name = "car" )
public class CarEntity {
    @Id
    @Column( "id" )
    private Long id;
    @Column( "model" )
    private String model;
    @Column( "carregistration" )
    private String carRegistration;
    @Column( "entry" )
    private Instant entry;
    @Column( "exit" )
    private Instant exit;
    @Column( "active" )
    private Boolean active;
    @Column( "adviser" )
    private String adviser;
}
