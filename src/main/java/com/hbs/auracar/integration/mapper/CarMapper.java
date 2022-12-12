package com.hbs.auracar.integration.mapper;

import com.hbs.auracar.integration.entity.CarEntity;
import com.hbs.auracar.service.dto.CarDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper( componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR )
public interface CarMapper {
    //    @Mapping( source = "", target = "entry" )
    //    @Mapping( source = "", target = "exit" )
    CarEntity toEntity( CarDto carDto );

    @Mapping( source = "entry", target = "arrivalDay" )
    @Mapping( source = "entry", target = "entryTime" )
    @Mapping( source = "exit", target = "exitTime" )
    CarDto toDto( CarEntity carEntity );

    void update( CarEntity carEntity, @MappingTarget CarEntity previousCarEntity );
}
