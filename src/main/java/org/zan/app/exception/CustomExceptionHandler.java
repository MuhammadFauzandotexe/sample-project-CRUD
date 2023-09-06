package org.zan.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.zan.app.dto.CommonResponseDTO;

@ControllerAdvice
public class CustomExceptionHandler {
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
    @ExceptionHandler(SampleAppException.class)
    public ResponseEntity<CommonResponseDTO<?>> handleSampleAppException(RuntimeException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                CommonResponseDTO
                        .builder()
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("there is an server error")
                        .data(e.getMessage())
                        .build()
        );
    }
}

