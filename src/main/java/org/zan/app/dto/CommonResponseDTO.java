package org.zan.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class CommonResponseDTO<T> {
    private Integer statusCode;
    private String message;
    private T data;
}
