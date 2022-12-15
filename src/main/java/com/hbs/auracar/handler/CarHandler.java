package com.hbs.auracar.handler;

import com.hbs.auracar.service.ICarService;
import com.hbs.auracar.service.dto.CarDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@Component
public class CarHandler {
    private final ICarService iCarService;

    public CarHandler( ICarService iCarService ) {
        this.iCarService = iCarService;
    }

    public Mono<ServerResponse> getCars( ServerRequest serverRequest ) {
        log.info( "Recovery Cars" );
        return ServerResponse.ok().body(
            iCarService.getCars(), CarDto.class );
    }

    public Mono<ServerResponse> getCar( ServerRequest serverRequest ) {
        log.info( "Recovery Car" );
        return ServerResponse.ok().body( iCarService.getCar(
            Long.valueOf( Objects.requireNonNull( serverRequest.pathVariable( "id" ) ) ) ), CarDto.class );
    }

    public Mono<ServerResponse> deleteCar( ServerRequest serverRequest ) {
        log.info( "Delete car" );
        return ServerResponse.ok().body( iCarService.deleteCar(
            Long.valueOf( Objects.requireNonNull( serverRequest.pathVariable( "id" ) ) ) ), CarDto.class );
    }

    public Mono<ServerResponse> createCar( ServerRequest serverRequest ) {
        Mono<CarDto> carDto = serverRequest.bodyToMono( CarDto.class );
        log.info( "Create car" );
        return ServerResponse.ok().body( carDto.flatMap( iCarService :: createCar )
            , CarDto.class );
    }

    public Mono<ServerResponse> updateCar( ServerRequest serverRequest ) {
        Mono<CarDto> carDto = serverRequest.bodyToMono( CarDto.class );
        log.info( "Update car" );
        return ServerResponse.ok().body( carDto.flatMap( iCarService :: updateCar )
            , CarDto.class );
    }
}

