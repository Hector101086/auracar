package com.hbs.auracar.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiError {
    private HttpStatus status;
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss" )
    private LocalDateTime timestamp;
    private String code;
    private String exceptionMessage;
    private String stackTrace;
    private String descriptionURL;
    private String requestKey;

    public String getDescriptionURL() {
        return descriptionURL;
    }

    public void setDescriptionURL( String descriptionURL ) {
        this.descriptionURL = descriptionURL;
    }

    public ApiError() {
        timestamp = LocalDateTime.now();
    }

    public ApiError setStatus( HttpStatus status ) {
        this.status = status;
        return this;
    }

    public ApiError setCode( String code ) {
        this.code = code;
        return this;
    }

    public ApiError setExceptionMessage( String exceptionMessage ) {
        this.exceptionMessage = exceptionMessage;
        return this;
    }

    public ApiError setStackTrace( String stackTrace ) {
        this.stackTrace = stackTrace;
        return this;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getCode() {
        return code;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public ApiError setMessageFromThrowable( Throwable ex ) {
        this.exceptionMessage = ex.getLocalizedMessage();
        return this;
    }

    public ApiError setStackFromThrowable( Throwable ex ) {
        this.stackTrace = ExceptionUtils.getStackTrace( ex );
        return this;
    }

    public String getRequestKey() {
        return requestKey;
    }

    public ApiError setRequestKey( String requestKey ) {
        this.requestKey = requestKey;
        return this;
    }
}
