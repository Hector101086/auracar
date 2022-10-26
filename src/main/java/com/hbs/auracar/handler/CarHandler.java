package com.hbs.auracar.handler;

import com.hbs.auracar.service.ICarService;
import com.hbs.auracar.service.dto.CarDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@Component
public class CarHandler {
    private final ICarService iCarService;

    public CarHandler(ICarService iCarService) {
        this.iCarService = iCarService;
    }

    public Mono<ServerResponse> getCars(ServerRequest serverRequest) {
        log.info("Recovery Cars");
        return ServerResponse.ok().body(
                iCarService.getCars(), CarDto.class);
    }

    public Mono<ServerResponse> deleteCar(ServerRequest serverRequest) {
        MultiValueMap<String, String> queryParams = serverRequest.queryParams();
        log.info("Delete car");
        return ServerResponse.ok().body(iCarService.deleteCar(
                        Long.valueOf(Objects.requireNonNull(queryParams.getFirst("idCar"))))
                , CarDto.class);
    }

    public Mono<ServerResponse> createCar(ServerRequest serverRequest) {
        Mono<CarDto> carDto = serverRequest.bodyToMono(CarDto.class);
        log.info("Create car");
        return ServerResponse.ok().body(iCarService.createCar(
                        carDto)
                , CarDto.class);
    }
}

