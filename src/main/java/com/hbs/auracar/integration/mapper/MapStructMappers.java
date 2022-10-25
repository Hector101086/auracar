package com.hbs.auracar.integration.mapper;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MapStructMappers {
    private final CarMapper carMapper;

//    public static MapStructMappers newInstance() {
//        return new MapStructMappers(
//            new CarMapperImpl()
//        );
//    }
}
