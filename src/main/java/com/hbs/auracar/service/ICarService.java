package com.hbs.auracar.service;

import com.hbs.auracar.service.dto.CarDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICarService {
    Flux<CarDto> getCars();

    Mono<CarDto> getCar( Long IdCar );

    Mono<CarDto> deleteCar( Long idCar );

    Mono<CarDto> createCar( CarDto carDto );

    Mono<CarDto> updateCar( CarDto carDto );
}
