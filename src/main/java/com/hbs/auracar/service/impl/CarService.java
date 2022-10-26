package com.hbs.auracar.service.impl;

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

    public CarService(CarRepository carRepository, MapStructMappers mapStructMappers) {
        this.carRepository = carRepository;
        this.mapStructMappers = mapStructMappers;
    }

    @Override
    public Flux<CarDto> getCars() {
//        return portCallsApi.getPortCalls( days, executionMode )
//            .collectList()
//            .filter( portCallDtos -> ! portCallDtos.isEmpty() )
//            .switchIfEmpty( Mono.error( new ApiException( "BKE0002", "No data" ) ) )
//            .map( ThrowingFunction.unchecked( objectMapper :: writeValueAsString ) )
//            .map( kafkaProducer :: sendMessageOfficialPortCalls )
//            .onErrorResume( error -> Mono.error( new ApiException( "BKE0002", error ) ) );
        return carRepository.findAll().map(carEntity -> mapStructMappers.getCarMapper().toDto(carEntity));
    }

    @Override
    public Mono<CarDto> deleteCar(Long idCare) {
//        return iotAdapter.getExpectedArrivals( timespan, portId, shipType, executionMode )
//            .collectList()
//            .filter( nonOfficialBerthingDtos -> ! nonOfficialBerthingDtos.isEmpty() )
//            .switchIfEmpty( Mono.error( new ApiException( "BKE0003", "No data" ) ) )
//            .map( ThrowingFunction.unchecked( objectMapper :: writeValueAsString ) )
//            .map( kafkaProducer :: sendMessageNonOfficialBerthings )
//            .onErrorResume( error -> Mono.error( new ApiException( "BKE0003", error ) ) );

        carRepository.deleteById(idCare);
        return null;
    }

    @Override
    public Mono<CarDto> createCar(Mono<CarDto> carDto) {
//        return iotAdapter.getExpectedArrivals( timespan, portId, shipType, executionMode )
//                .collectList()
//                .filter( nonOfficialBerthingDtos -> ! nonOfficialBerthingDtos.isEmpty() )
//                .switchIfEmpty( Mono.error( new ApiException( "BKE0003", "No data" ) ) )
//                .map( ThrowingFunction.unchecked( objectMapper :: writeValueAsString ) )
//                .map( kafkaProducer :: sendMessageNonOfficialBerthings )
//                .onErrorResume( error -> Mono.error( new ApiException( "BKE0003", error ) ) );
        return null;
    }
}
