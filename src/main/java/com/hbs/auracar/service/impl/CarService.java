package com.hbs.auracar.service.impl;

import com.hbs.auracar.config.exception.ApiException;
import com.hbs.auracar.integration.entity.CarEntity;
import com.hbs.auracar.integration.mapper.MapStructMappers;
import com.hbs.auracar.integration.repository.CarRepository;
import com.hbs.auracar.service.ICarService;
import com.hbs.auracar.service.dto.CarDto;
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
    public Flux<CarDto> getCars() {
        return carRepository.findByActive( true )
            .switchIfEmpty( Mono.error( new ApiException( "BKE0002", "No data" ) ) )
            .map( carEntity -> mapStructMappers.getCarMapper().toDto( carEntity ) )
            .onErrorResume( error -> Mono.error( new ApiException( "BKE0002", error ) ) );
    }

    @Override
    public Mono<CarDto> getCar( Long IdCar ) {
        return carRepository.findByIdAndActive( IdCar, true )
            .switchIfEmpty( Mono.error( new ApiException( "BKE0002", "No data" ) ) )
            .map( carEntity -> mapStructMappers.getCarMapper().toDto( carEntity ) )
            .onErrorResume( error -> Mono.error( new ApiException( "BKE0002", error ) ) );
    }

    @Override
    public Mono<CarDto> createCar( CarDto carDto ) {
        carDto.setId( null );
        return carRepository.save( mapStructMappers.getCarMapper().toEntity( carDto ) )
            .map( carEntity -> mapStructMappers.getCarMapper().toDto( carEntity ) )
            .onErrorResume( error -> Mono.error( new ApiException( "BKE0004", error ) ) );
    }

    @Override
    public Mono<CarDto> deleteCar( Long idCar ) {
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
    public Mono<CarDto> updateCar( CarDto carDto ) {
        return carRepository.findById( carDto.getId() )
            .switchIfEmpty( Mono.error( new ApiException( "BKE0005", "Car with id not found: " + carDto.getId() ) ) )
            .flatMap( carEntityPrevious -> {
                CarEntity carEntity = mapStructMappers.getCarMapper().toEntity( carDto );
                mapStructMappers.getCarMapper().update( carEntity, carEntityPrevious );
                return carRepository.save( carEntityPrevious );
            } )
            .map( carEntitySave -> mapStructMappers.getCarMapper().toDto( carEntitySave ) )
            .onErrorResume( error -> Mono.error( new ApiException( "BKE0005", error ) ) );
    }
}
