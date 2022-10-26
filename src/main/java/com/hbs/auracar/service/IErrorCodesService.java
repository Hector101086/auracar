package com.hbs.auracar.service;

import lombok.Getter;
import lombok.Setter;

public interface IErrorCodesService {

    /**
     * Class to represent an Error Code Description in JSON
     */
    @Getter
    @Setter
    class ErrorCode {
        private String message;
        private String troubleshoot;

    }

    /**
     * Get the JSON describing all error codes
     *
     * @param languages Language codes
     * @return string of the JSON object
     */
    String getCodes(String languages);

    /**
     * Get the object describing a given error code
     *
     * @param languages Language codes
     * @param code      Error code
     * @return ErrorCode object
     */
    ErrorCode getCodeDescription(String languages, String code);
}
