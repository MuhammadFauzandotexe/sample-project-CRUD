package org.zan.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Data Transfer Object (DTO) representing an order request.
 * It includes information such as the item ID, quantity, and order number.
 */
@Data
public class OrderRequestDTO {

    /**
     * The ID of the item to be ordered.
     */
    @JsonProperty("item_id")
    private Integer itemId;

    /**
     * The quantity of the item to be ordered. It should be a non-negative integer.
     */
    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity must be a non-negative integer")
    private Integer quantity;;

    /**
     * The order number associated with the order request.
     */
    @JsonProperty("order_no")
    private String orderNo;
}
