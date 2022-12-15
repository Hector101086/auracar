package com.hbs.auracar.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class CarDto {
    private Long id;
    private String model;
    private String carRegistration;
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Europe/Madrid" )
    private Instant arrivalDay;
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Europe/Madrid" )
    private Instant entryTime;
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Europe/Madrid" )
    private Instant exitTime;
    private Long countdown;
    private Boolean active;
}
