package org.zan.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
/**
 * Data Transfer Object (DTO) representing an update request for an order.
 * It includes information such as the order's ID, item ID, quantity, and order number.
 */
@Data
public class OrderUpdateDTO {

    /**
     * The unique identifier of the order to be updated.
     */
    private Integer id;

    /**
     * The updated ID of the item associated with the order.
     */
    @JsonProperty("item_id")
    private Integer itemId;

    /**
     * The updated quantity of the item in the order. It should be a non-negative integer.
     */
    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity must be a non-negative integer")
    private Integer quantity;;

    /**
     * The updated order number associated with the order.
     */
    @JsonProperty("order_no")
    private String orderNo;
}
