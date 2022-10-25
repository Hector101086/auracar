package com.hbs.auracar.service;

import com.hbs.auracar.integration.entity.CarEntity;
import com.hbs.auracar.integration.mapper.MapStructMappers;
import com.hbs.auracar.integration.repository.CarRepository;
import com.hbs.auracar.service.dto.CarDto;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    private final CarRepository carRepository;

    private final MapStructMappers mapStructMappers;

    public CarService(CarRepository carRepository, MapStructMappers mapStructMappers) {
        this.carRepository = carRepository;
        this.mapStructMappers = mapStructMappers;
    }

    public CarDto save(CarDto car) {
        CarEntity carEntity = mapStructMappers.getCarMapper().toEntity(car);
        return mapStructMappers.getCarMapper().toDto(carRepository.save(carEntity));
    }

    public void delete(CarDto car) {
        CarEntity carEntity = mapStructMappers.getCarMapper().toEntity(car);
        carRepository.delete(carEntity);
    }
}
