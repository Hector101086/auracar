package com.hbs.auracar.service;

import com.hbs.auracar.service.dto.CarDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICarService {
    Flux<CarDto> getCars();

    Mono<CarDto> deleteCar(Long idCare);

    Mono<CarDto> createCar(Mono<CarDto> carDto);
}
