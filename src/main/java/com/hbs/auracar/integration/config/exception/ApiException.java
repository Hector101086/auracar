package com.hbs.auracar.integration.config.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class ApiException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 9172040739482898593L;
    private final HttpStatus status;
    private final String code;

    public ApiException( HttpStatus status, String code, String message ) {
        super( message );
        this.status = status;
        this.code = code;
    }

    public ApiException( String code, String message ) {
        super( message );
        this.code = code;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ApiException( String code ) {
        super( code );
        this.code = code;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ApiException( HttpStatus status, String code, Throwable ex ) {
        super( ex );
        this.status = status;
        this.code = code;
    }

    public ApiException( HttpStatus status, String code ) {
        super( code );
        this.status = status;
        this.code = code;
    }

    public ApiException( String code, Throwable ex ) {
        this( HttpStatus.INTERNAL_SERVER_ERROR, code, ex );
    }

    public ApiException( HttpStatus status, Throwable ex ) {
        this( status, "BKE0001", ex );
    }

    public ApiException( Throwable ex ) {
        this( HttpStatus.INTERNAL_SERVER_ERROR, "BKE0001", ex );
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }
}
