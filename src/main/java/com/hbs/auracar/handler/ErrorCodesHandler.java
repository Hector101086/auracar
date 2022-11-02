package com.hbs.auracar.handler;

import com.hbs.auracar.service.IErrorCodesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ErrorCodesHandler {
    private final IErrorCodesService errorCodesService;

    public ErrorCodesHandler( IErrorCodesService errorCodesService ) {
        this.errorCodesService = errorCodesService;
    }

    public Mono<ServerResponse> getCodes( ServerRequest serverRequest ) {
        MultiValueMap<String, String> queryParams = serverRequest.queryParams();
        return ServerResponse.ok()
            .bodyValue( errorCodesService.getCodes( queryParams.getFirst( "lang" ) ) );
    }

    public Mono<ServerResponse> getCode( ServerRequest serverRequest ) {
        return ServerResponse.ok()
            .bodyValue( errorCodesService.getCodeDescription(
                serverRequest.headers().firstHeader( "languages" ),
                serverRequest.pathVariable( "code" ) ) );
    }
}
