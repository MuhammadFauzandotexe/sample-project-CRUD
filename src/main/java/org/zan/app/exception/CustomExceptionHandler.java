package org.zan.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.zan.app.dto.CommonResponseDTO;


/**
 * Global exception handler for the application.
 * This class handles exceptions of type {@link Exception} and {@link SampleCrudException}.
 * It returns a standardized error response in the form of a {@link CommonResponseDTO}.
 */
@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * Handles generic exceptions.
     *
     * @param e The exception to handle.
     * @return A {@link ResponseEntity} with an error response in the form of a {@link CommonResponseDTO}.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponseDTO<?>> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                CommonResponseDTO
                        .builder()
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("there is an server error")
                        .data(e.getMessage())
                        .build()
        );
    }

    /**
     * Handles exceptions of type {@link SampleCrudException}.
     *
     * @param e The exception to handle.
     * @return A {@link ResponseEntity} with an error response in the form of a {@link CommonResponseDTO}.
     */
    @ExceptionHandler(SampleCrudException.class)
    public ResponseEntity<CommonResponseDTO<?>> handleSampleAppException(SampleCrudException e){
        return ResponseEntity.status(e.getHttpStatus()).body(
                CommonResponseDTO
                        .builder()
                        .statusCode(e.getHttpStatus().value())
                        .message("Not Found")
                        .data(e.getMessage())
                        .build()
        );
    }
}

