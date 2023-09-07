package org.zan.app.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * This class is used to represent an unexpected or unauthorized error
 * that has occurred and cannot be corrected without modifying the code.
 *
 * @author Muhammad Fauzan
 */

@Getter
public class SampleCrudException extends RuntimeException {
    private final HttpStatus httpStatus;

    public SampleCrudException(String message, HttpStatus httpStatus) {
        super("Sample App Exception: " + message);
        this.httpStatus = httpStatus;
    }
    public SampleCrudException(String message) {
        this(message, HttpStatus.NOT_FOUND);
    }

}





//log4j2.xml
//traceId, spanId
//spring sleuth;