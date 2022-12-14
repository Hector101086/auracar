package com.hbs.auracar.integration.mapper;

import com.hbs.auracar.integration.entity.CarEntity;
import com.hbs.auracar.service.dto.CarDto;
import org.mapstruct.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Mapper( componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR )
public interface CarMapper {
    @Mapping( target = "id", ignore = true )
    @Mapping( source = "entryTime", target = "entry" )
    @Mapping( source = "exitTime", target = "exit" )
    CarEntity toEntity( CarDto carDto );

    @BeforeMapping
    default void beforeMapping( CarDto carDto ) {
        Instant entry = Instant.now();
        Instant exit = entry.plusSeconds( carDto.getCountdown() * 60 );
        if( carDto.getEntryTime() == null ) {
            carDto.setEntryTime( entry );
        }
        if( carDto.getExitTime() == null ) {
            carDto.setExitTime( exit );
        }
    }

    @Mapping( source = "entry", target = "arrivalDay" )
    @Mapping( source = "entry", target = "entryTime" )
    @Mapping( source = "exit", target = "exitTime" )
    CarDto toDto( CarEntity carEntity );

    @AfterMapping
    default void afterMapping( @MappingTarget CarDto carDto ) {
        long minutes = ChronoUnit.MINUTES.between( Instant.now(), carDto.getExitTime() );
        carDto.setCountdown( minutes <= 0 ? 0 : minutes );
    }

    void update( CarEntity carEntity, @MappingTarget CarEntity previousCarEntity );
}
