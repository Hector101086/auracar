package com.hbs.auracar.integration.config.exception;

import com.hbs.auracar.integration.config.exception.dto.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@Component
@Order( Ordered.HIGHEST_PRECEDENCE )
@Slf4j
public class ApiExceptionHandler extends AbstractErrorWebExceptionHandler {
    public ApiExceptionHandler( ErrorAttributes errorAttributes, ApplicationContext applicationContext,
        ServerCodecConfigurer serverCodecConfigurer ) {
        super( errorAttributes, new WebProperties.Resources(), applicationContext );
        super.setMessageWriters( serverCodecConfigurer.getWriters() );
        super.setMessageReaders( serverCodecConfigurer.getReaders() );
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction( ErrorAttributes errorAttributes ) {
        return RouterFunctions.route( RequestPredicates.all(),
            request -> renderErrorResponse( request, errorAttributes ) );
    }

    private Mono<ServerResponse> renderErrorResponse( final ServerRequest request, ErrorAttributes errorAttributes ) {
        Throwable error = errorAttributes.getError( request );
        if( error instanceof ApiException apiException ) {
            return buildApiError( apiException, apiException.getCode(), apiException.getStatus(), request );
        }
        return buildApiError( error, request );
    }

    private Mono<ServerResponse> buildApiError( Throwable ex, String code, HttpStatus status, ServerRequest request ) {
        ApiError apiError = new ApiError()
            .setCode( code == null ? "BKE0000" : code )
            .setStatus( status == null ? HttpStatus.INTERNAL_SERVER_ERROR : status )
            .setMessageFromThrowable( ex );
        apiError.setDescriptionURL( getDescriptionURL( request, apiError ) );
        return ServerResponse.status( apiError.getStatus() ).body( BodyInserters.fromValue( apiError ) );
    }

    private Mono<ServerResponse> buildApiError( Throwable ex, ServerRequest request ) {
        return buildApiError( ex, null, null, request );
    }

    private String getDescriptionURL( ServerRequest request, ApiError apiError ) {
        return request.uri().getScheme() + "://" + request.uri().getAuthority() + "/error/codes/" + apiError.getCode();
    }
}
