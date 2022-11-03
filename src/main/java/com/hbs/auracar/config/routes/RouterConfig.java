package com.hbs.auracar.config.routes;

import com.hbs.auracar.handler.CarHandler;
import com.hbs.auracar.handler.ErrorCodesHandler;
import com.hbs.auracar.service.IErrorCodesService;
import com.hbs.auracar.service.dto.ApiError;
import com.hbs.auracar.service.dto.CarDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Slf4j
@Configuration
public class RouterConfig {
    @Bean
    @RouterOperations(
        {
            @RouterOperation( path = "/api/v1/cars", produces = { MediaType.APPLICATION_JSON_VALUE },
                method = RequestMethod.GET, beanClass = CarHandler.class, beanMethod = "getCars",
                operation = @Operation( operationId = "getCars",
                    summary = "Car list", tags = { "Cars" }, responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",
                        description = "Successful operation",
                        content = @Content( schema = @Schema( implementation = CarDto.class ) ) ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",
                        description = "Bad Request",
                        content = @Content( schema = @Schema( implementation = ApiError.class ) ) ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",
                        description = "Internal server error",
                        content = @Content( schema = @Schema( implementation = ApiError.class ) ) ) }
                )
            ),
            @RouterOperation( path = "/api/v1/car", produces = { MediaType.APPLICATION_JSON_VALUE },
                method = RequestMethod.GET, beanClass = CarHandler.class, beanMethod = "getCar",
                operation = @Operation( operationId = "getCar",
                    summary = "Car list", tags = { "Cars" }, responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",
                        description = "Successful operation",
                        content = @Content( schema = @Schema( implementation = CarDto.class ) ) ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",
                        description = "Bad Request",
                        content = @Content( schema = @Schema( implementation = ApiError.class ) ) ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",
                        description = "Internal server error",
                        content = @Content( schema = @Schema( implementation = ApiError.class ) ) ) }, parameters = {
                    @Parameter( required = true, in = ParameterIn.QUERY, name = "idCar",
                        schema = @Schema( type = "string" ) ) }
                )
            ),
            @RouterOperation( path = "/api/v1/car", produces = { MediaType.APPLICATION_JSON_VALUE },
                method = RequestMethod.DELETE, beanClass = CarHandler.class, beanMethod = "deleteCar",
                operation = @Operation( operationId = "deleteCar",
                    summary = "Delete car", tags = { "Cars" }, responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",
                        description = "Successful operation",
                        content = @Content( schema = @Schema( implementation = CarHandler.class ) ) ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",
                        description = "Bad Request",
                        content = @Content( schema = @Schema( implementation = ApiError.class ) ) ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",
                        description = "Internal server error",
                        content = @Content( schema = @Schema( implementation = ApiError.class ) ) ) }, parameters = {
                    @Parameter( required = true, in = ParameterIn.QUERY, name = "idCar",
                        schema = @Schema( type = "string" ) ) }
                )
            ),
            @RouterOperation( path = "/api/v1/car", produces = { MediaType.APPLICATION_JSON_VALUE },
                method = RequestMethod.POST, beanClass = CarHandler.class, beanMethod = "createCar",
                operation = @Operation( operationId = "createCar",
                    summary = "Create car", tags = { "Cars" }, responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",
                        description = "Successful operation",
                        content = @Content( schema = @Schema( implementation = CarHandler.class ) ) ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",
                        description = "Bad Request",
                        content = @Content( schema = @Schema( implementation = ApiError.class ) ) ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",
                        description = "Internal server error",
                        content = @Content( schema = @Schema( implementation = ApiError.class ) ) ) },
                    requestBody = @RequestBody( required = true, description = "Enter Request body as Json Object",
                        content = @Content( schema = @Schema( implementation = CarDto.class ) ) )
                )
            ),
            @RouterOperation( path = "/api/v1/car", produces = { MediaType.APPLICATION_JSON_VALUE },
                method = RequestMethod.PATCH, beanClass = CarHandler.class, beanMethod = "updateCar",
                operation = @Operation( operationId = "updateCar",
                    summary = "Update car", tags = { "Cars" }, responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",
                        description = "Successful operation",
                        content = @Content( schema = @Schema( implementation = CarHandler.class ) ) ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",
                        description = "Bad Request",
                        content = @Content( schema = @Schema( implementation = ApiError.class ) ) ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",
                        description = "Internal server error",
                        content = @Content( schema = @Schema( implementation = ApiError.class ) ) ) },
                    requestBody = @RequestBody( required = true, description = "Enter Request body as Json Object",
                        content = @Content( schema = @Schema( implementation = CarDto.class ) ) )
                )
            )
        } )
    public RouterFunction<ServerResponse> routerCars( CarHandler carHandler ) {
        return RouterFunctions.route( GET( "/api/v1/cars" ), carHandler :: getCars )
            .andRoute( GET( "/api/v1/car" ), carHandler :: getCar )
            .andRoute( DELETE( "/api/v1/car" ), carHandler :: deleteCar )
            .andRoute( POST( "/api/v1/car" ), carHandler :: createCar )
            .andRoute( PATCH( "/api/v1/car" ), carHandler :: updateCar );
    }

    @Bean
    @RouterOperations(
        {
            @RouterOperation( path = "/error/codes", produces = { MediaType.APPLICATION_JSON_VALUE },
                method = RequestMethod.GET, beanClass = ErrorCodesHandler.class, beanMethod = "getCodes",
                operation = @Operation( operationId = "getCodes",
                    summary = "Codes", tags = { "Errors" }, responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",
                        description = "Successful operation",
                        content = @Content( schema = @Schema( implementation = IErrorCodesService.ErrorCode.class ) ) ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",
                        description = "Bad Request",
                        content = @Content( schema = @Schema( implementation = ApiError.class ) ) ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",
                        description = "Internal server error",
                        content = @Content( schema = @Schema( implementation = ApiError.class ) ) ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "502",
                        description = "Bad gateway",
                        content = @Content( schema = @Schema( implementation = ApiError.class ) ) ) }, parameters = {
                    @Parameter( in = ParameterIn.QUERY, name = "lang", schema = @Schema( type = "string" ) ) }
                )
            ),
            @RouterOperation( path = "/error/codes/{code}", produces = { MediaType.APPLICATION_JSON_VALUE },
                method = RequestMethod.GET, beanClass = ErrorCodesHandler.class, beanMethod = "getCode",
                operation = @Operation( operationId = "getCode",
                    summary = "Code", tags = { "Errors" }, responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",
                        description = "Successful operation",
                        content = @Content( schema = @Schema( implementation = IErrorCodesService.ErrorCode.class ) ) ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",
                        description = "Bad Request",
                        content = @Content( schema = @Schema( implementation = ApiError.class ) ) ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",
                        description = "Internal server error",
                        content = @Content( schema = @Schema( implementation = ApiError.class ) ) ) }, parameters = {
                    @Parameter( in = ParameterIn.HEADER, name = "languages", schema = @Schema( type = "string" ) ),
                    @Parameter( in = ParameterIn.PATH, name = "code", schema = @Schema( type = "string" ) ) }
                )
            )
        } )
    public RouterFunction<ServerResponse> routerErrors( ErrorCodesHandler errorCodesHandler ) {
        return RouterFunctions.route( GET( "/error/codes" ), errorCodesHandler :: getCodes )
            .andRoute( GET( "/error/codes/{code}" ), errorCodesHandler :: getCode );
    }
}
