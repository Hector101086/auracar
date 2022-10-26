package com.hbs.auracar.integration.repository;

import com.hbs.auracar.integration.entity.CarEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends ReactiveCrudRepository<CarEntity, Long> {
}
