package com.hbs.auracar.service;

import com.hbs.auracar.service.dto.Car;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICarService {
    Flux<Car> getCars();

    Mono<Car> getCar( Long idCar );

    Mono<Car> deleteCar( Long idCar );

    Mono<Car> createCar( Car car );

    Mono<Car> updateCar( Car car );
}
