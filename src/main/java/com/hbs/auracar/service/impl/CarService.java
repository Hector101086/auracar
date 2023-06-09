package com.hbs.auracar.service.impl;

import com.hbs.auracar.exception.ApiException;
import com.hbs.auracar.integration.mapper.MapStructMappers;
import com.hbs.auracar.integration.model.CarEntity;
import com.hbs.auracar.integration.repository.CarRepository;
import com.hbs.auracar.service.ICarService;
import com.hbs.auracar.service.dto.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class CarService implements ICarService {
    private final CarRepository carRepository;
    private final MapStructMappers mapStructMappers;

    public CarService( CarRepository carRepository, MapStructMappers mapStructMappers ) {
        this.carRepository = carRepository;
        this.mapStructMappers = mapStructMappers;
    }

    @Override
    public Flux<Car> getCars() {
        return carRepository.findByActiveOrderById( true )
            .map( carEntity -> mapStructMappers.getCarMapper().toDto( carEntity ) )
            .onErrorResume( error -> Mono.error( new ApiException( "BKE0002", error ) ) );
    }

    @Override
    public Mono<Car> getCar( Long idCar ) {
        return carRepository.findByIdAndActiveOrderById( idCar, true )
            .map( carEntity -> mapStructMappers.getCarMapper().toDto( carEntity ) )
            .onErrorResume( error -> Mono.error( new ApiException( "BKE0002", error ) ) );
    }

    @Override
    public Mono<Car> createCar( Car car ) {
        car.setId( null );
        return carRepository.save( mapStructMappers.getCarMapper().toEntity( car ) )
            .map( carEntity -> mapStructMappers.getCarMapper().toDto( carEntity ) )
            .onErrorResume( error -> Mono.error( new ApiException( "BKE0004", error ) ) );
    }

    @Override
    public Mono<Car> deleteCar( Long idCar ) {
        return carRepository.findById( idCar )
            .switchIfEmpty( Mono.error( new ApiException( "BKE0003", "Car with id not found: " + idCar ) ) )
            .flatMap( carEntity -> {
                carEntity.setActive( false );
                return carRepository.save( carEntity );
            } )
            .map( carEntitySave -> mapStructMappers.getCarMapper().toDto( carEntitySave ) )
            .onErrorResume( error -> Mono.error( new ApiException( "BKE0003", error ) ) );
    }

    @Override
    public Mono<Car> updateCar( Car car ) {
        return carRepository.findById( car.getId() )
            .switchIfEmpty( Mono.error( new ApiException( "BKE0005", "Car with id not found: " + car.getId() ) ) )
            .flatMap( carEntityPrevious -> {
                CarEntity carEntity = mapStructMappers.getCarMapper().toEntity( car );
                mapStructMappers.getCarMapper().update( carEntity, carEntityPrevious );
                return carRepository.save( carEntityPrevious );
            } )
            .map( carEntitySave -> mapStructMappers.getCarMapper().toDto( carEntitySave ) )
            .onErrorResume( error -> Mono.error( new ApiException( "BKE0005", error ) ) );
    }
}
