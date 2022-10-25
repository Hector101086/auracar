package com.hbs.auracar.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class CarDto {

    private Integer id;
    private String model;
    private String carRegistration;
    private Instant entry;
    private Instant exit;

}
