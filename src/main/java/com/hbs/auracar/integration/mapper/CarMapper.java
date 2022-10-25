package com.hbs.auracar.integration.mapper;

import com.hbs.auracar.integration.entity.CarEntity;
import com.hbs.auracar.service.dto.CarDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CarMapper {

    CarEntity toEntity(CarDto carDto);

    CarDto toDto(CarEntity carEntity);

    void update(CarEntity carEntity, @MappingTarget CarEntity previousCarEntity);
}
