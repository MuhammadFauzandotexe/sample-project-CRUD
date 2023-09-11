package org.zan.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Data Transfer Object (DTO) representing an order request.
 * It includes information such as the item ID, quantity, and order number.
 */
@Getter
@Setter
public class OrderRequestDTO {

    /**
     * The ID of the item to be ordered.
     */
    private UUID itemId;

    /**
     * The quantity of the item to be ordered.
     */
    private Integer quantity;;

    /**
     * The order number associated with the order request.
     */
    private String orderNo;
}
