package org.zan.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Data Transfer Object (DTO) representing an update request for an order.
 * It includes information such as the order's ID, item ID, quantity, and order number.
 */
@Getter
@Setter
public class OrderUpdateDTO {

    /**
     * The unique identifier of the order to be updated.
     */
    private UUID id;

    /**
     * The updated ID of the item associated with the order.
     */
    private UUID itemId;

    /**
     * The updated quantity of the item in the order. It should be a non-negative integer.
     */
    private Integer quantity;;

    /**
     * The updated order number associated with the order.
     */
    private String orderNo;
}
