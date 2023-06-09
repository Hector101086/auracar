package com.hbs.auracar.integration.mapper;

import com.hbs.auracar.integration.model.CarEntity;
import com.hbs.auracar.service.dto.Car;
import org.mapstruct.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Mapper( componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR )
public interface CarMapper {
    @Mapping( target = "id", ignore = true )
    @Mapping( source = "entryTime", target = "entry" )
    @Mapping( source = "exitTime", target = "exit" )
    CarEntity toEntity( Car car );

    @BeforeMapping
    default void beforeMapping( Car car ) {
        Instant entry = Instant.now();
        Instant exit = entry.plusSeconds( car.getCountdown() * 60 );
        if( car.getEntryTime() == null ) {
            car.setEntryTime( entry );
        }
        if( car.getExitTime() == null ) {
            car.setExitTime( exit );
        }
    }

    @Mapping( source = "entry", target = "arrivalDay" )
    @Mapping( source = "entry", target = "entryTime" )
    @Mapping( source = "exit", target = "exitTime" )
    Car toDto( CarEntity carEntity );

    @AfterMapping
    default void afterMapping( @MappingTarget Car car ) {
        long minutes = ChronoUnit.MINUTES.between( Instant.now(), car.getExitTime() );
        car.setCountdown( minutes <= 0 ? 0 : minutes );
    }

    void update( CarEntity carEntity, @MappingTarget CarEntity previousCarEntity );
}
