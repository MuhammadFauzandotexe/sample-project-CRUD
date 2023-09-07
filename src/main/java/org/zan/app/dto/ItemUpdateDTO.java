package org.zan.app.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing an update request for an item.
 * It includes information such as the item's ID, name, and price.
 */
@Getter
@Setter
public class ItemUpdateDTO {

    /**
     * The unique identifier of the item to be updated.
     */
    private String id;

    /**
     * The updated name of the item. It should be a descriptive name for the item.
     */
    private String name;

    /**
     * The updated price of the item. It should be a non-negative integer representing
     * the item's price in a certain currency.
     */
    @NotNull(message = "Price cannot be null")
    @Min(value = 1, message = "Price must be a non-negative integer")
    private Integer price;
}
