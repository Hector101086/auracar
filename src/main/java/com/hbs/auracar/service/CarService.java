package com.hbs.auracar.service;

import com.hbs.auracar.repository.CarRepository;
import com.hbs.auracar.repository.entity.CarEntity;
import com.hbs.auracar.service.dto.CarDto;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarDto save(CarDto car) {
        CarEntity carEntity = new CarEntity();//Mapper
        carRepository.save(carEntity);
        CarDto result = new CarDto();//Mapper
        return result;
    }

    public void delete(CarDto car) {
        CarEntity carEntity = new CarEntity();//Mapper
        carRepository.delete(carEntity);
    }
}
